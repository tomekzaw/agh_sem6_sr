/*
 * Copyright 2015, Google Inc. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *    * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *    * Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following disclaimer
 * in the documentation and/or other materials provided with the
 * distribution.
 *
 *    * Neither the name of Google Inc. nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package sr.grpc.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import sr.grpc.gen.*;
import sr.grpc.gen.Number;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArithmeticClient {
	private static final Logger logger = Logger.getLogger(ArithmeticClient.class.getName());

	private final ManagedChannel channel;
	private final CalculatorGrpc.CalculatorBlockingStub calcBlockingStub;
	private final AdvancedCalculatorGrpc.AdvancedCalculatorBlockingStub advCalcBlockingStub;
	private final CalculatorGrpc.CalculatorStub calcNonBlockingStub;

	private final StreamTesterGrpc.StreamTesterBlockingStub streamTesterBlockingStub;
	private final StreamTesterGrpc.StreamTesterStub streamTesterNonBlockingStub;


	/** Construct client connecting to HelloWorld server at {@code host:port}. */
	public ArithmeticClient(String host, int port) 
	{
		channel = ManagedChannelBuilder.forAddress(host, port)
				// Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid needing certificates.
				.usePlaintext(/*true*/)
				.build();

		calcBlockingStub = CalculatorGrpc.newBlockingStub(channel);

		advCalcBlockingStub = AdvancedCalculatorGrpc.newBlockingStub(channel);

		streamTesterBlockingStub = StreamTesterGrpc.newBlockingStub(channel);
		streamTesterNonBlockingStub = StreamTesterGrpc.newStub(channel);

		calcNonBlockingStub = CalculatorGrpc.newStub(channel);
	}

	public static void main(String[] args) throws Exception 
	{
		ArithmeticClient client = new ArithmeticClient("localhost", 50051);
		client.test();
	}

	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}


	public void test() throws InterruptedException
	{
		try {
			String line = null;
			java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(System.in)); 
			do
			{
				try
				{
					System.out.print("==> ");
					System.out.flush();
					line = in.readLine();
					if (line == null)
					{
						break;
					}
					if(line.equals("add"))
					{
						ArithmeticOpArguments request = ArithmeticOpArguments.newBuilder().setArg1(44).setArg2(55).build();
						ArithmeticOpResult result = calcBlockingStub.add(request);
						System.out.println(result.getRes());
					}
					if(line.equals("mul"))
					{
						MultiplyArguments request = MultiplyArguments.newBuilder().addAllNumbers(Arrays.asList(1, 2, 3, 4)).build();
						ArithmeticOpResult result = calcBlockingStub.multiply(request);
						System.out.println(result.getRes());
					}
					if(line.equals("add-deadline1"))
					{
						try {
							ArithmeticOpArguments request = ArithmeticOpArguments.newBuilder().setArg1(44).setArg2(55).build();
							ArithmeticOpResult result = calcBlockingStub.withDeadlineAfter(100, TimeUnit.MILLISECONDS).add(request);
							System.out.println(result.getRes());
						}
						catch(io.grpc.StatusRuntimeException e) {
							System.out.println("DEADLINE EXCEEDED");
						}
					} 
					if(line.equals("add-deadline2"))
					{
						try {
							ArithmeticOpArguments request = ArithmeticOpArguments.newBuilder().setArg1(4444).setArg2(5555).build();
							ArithmeticOpResult result = calcBlockingStub.withDeadlineAfter(100, TimeUnit.MILLISECONDS).add(request);
							System.out.println(result.getRes());
						}
						catch(io.grpc.StatusRuntimeException e) {
							System.out.println("DEADLINE EXCEEDED");
						}
					} 
					else if(line.equals("complex-sum"))
					{
						ComplexArithmeticOpArguments request = ComplexArithmeticOpArguments.newBuilder()
								.setOptype(OperationType.SUM).addAllArgs(Arrays.asList(4.0, 5.0, 8.5))
								.build();
						ComplexArithmeticOpResult result = advCalcBlockingStub.complexOperation(request);
						System.out.println(result.getRes());
					}
					else if(line.equals("complex-avg"))
					{
						ComplexArithmeticOpArguments request = ComplexArithmeticOpArguments.newBuilder()
								.setOptype(OperationType.AVG).addAllArgs(Arrays.asList(4.0, 5.0, 8.5))
								.build();
						ComplexArithmeticOpResult result = advCalcBlockingStub.complexOperation(request);
						System.out.println(result.getRes());
					}
					if(line.equals("async-add"))
					{
						ArithmeticOpArguments request = ArithmeticOpArguments.newBuilder().setArg1(4444).setArg2(5555).build();
						StreamObserver<ArithmeticOpResult> responseObserver = new StreamObserver<ArithmeticOpResult>()
						{
							@Override
							public void onError(Throwable t) 
							{
								System.out.println("gRPC ERROR");
							}

							@Override
							public void onCompleted() 
							{
							}

							@Override
							public void onNext(ArithmeticOpResult res) {
								System.out.println(res.getRes() + " (async)");									
							}
						};

						calcNonBlockingStub.add(request, responseObserver);							
					} 

					else if(line.equals("getprime"))
					{
						runGeneratePrimeNumbers();
					}
					else if(line.equals("countprime"))
					{
						runCountPrimeNumbers();
					}
				}
				catch (java.io.IOException ex)
				{
					System.err.println(ex);
				}
			}
			while (!line.equals("x"));
		} finally {
			shutdown();
		}

	}
	public void runGeneratePrimeNumbers()
	{
		Task request = Task.newBuilder().setMax(15).build();
		Iterator<Number> numbers;
		try {
			numbers = streamTesterBlockingStub.generatePrimeNumbers(request);
			while(numbers.hasNext())
			{
				//wypisuje siê z odstêpami czasowymi, wiêc strumieniowanie DZIA£A
				Number num = numbers.next();
				System.out.println("Number: " + num.getValue());
			}
		} catch (StatusRuntimeException ex) {
			logger.log(Level.WARNING, "RPC failed: {0}", ex.getStatus());
			return;
		}
	}

	public void runCountPrimeNumbers()
	{
		StreamObserver<Report> responseObserver = new StreamObserver<Report>() 
		{
			int count = -1;
			@Override
			public void onNext(Report result) 
			{
				//tylko jeden wynik
				count = result.getCount();
			}

			@Override
			public void onError(Throwable t) 
			{
				System.out.println("RPC ERROR");
			}

			@Override
			public void onCompleted() 
			{
				System.out.println("Result received: found " + count + " prime numbers");
			}
		};
		StreamObserver<Number> requestObserver = streamTesterNonBlockingStub.countPrimeNumbers(responseObserver);
		try {
			for (int i = 0; i < 20; ++i) {
				if(isPrime(i)) {
					Number number = Number.newBuilder().setValue(i).build();	
					System.out.println("Streaming data to the service...");
					requestObserver.onNext(number);
				}
			}
		} catch (RuntimeException e) {
			// Cancel RPC
			requestObserver.onError(e);
			throw e;
		}
		// Mark the end of requests
		requestObserver.onCompleted();
	}


	private boolean isPrime(int val)
	{
		if(val % 2 == 0) return false; 
		try { Thread.sleep(1000); } catch(java.lang.InterruptedException ex) { } 
		return true; //oczywiœcie to nieprawda ;)
	}

}
