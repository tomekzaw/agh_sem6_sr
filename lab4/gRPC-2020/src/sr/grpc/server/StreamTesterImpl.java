package sr.grpc.server;

import io.grpc.stub.StreamObserver;

import sr.grpc.gen.StreamTesterGrpc.StreamTesterImplBase;
import sr.grpc.gen.Task;
import sr.grpc.gen.Number;
import sr.grpc.gen.Report;

public class StreamTesterImpl extends StreamTesterImplBase
{
	@Override
	public void generatePrimeNumbers(Task request, StreamObserver<Number> responseObserver) 
	{
		System.out.println("generatePrimeNumbers");
		for (int i = 0; i < request.getMax(); i++) {
			if(isPrime(i)) { //zw³oka czasowa - dla obserwacji procesu strumieniowania				
				Number number = Number.newBuilder().setValue(i).build();
				responseObserver.onNext(number);
			}
		}
		responseObserver.onCompleted();
	}

	private boolean isPrime(int val) //tak naprawdê to zwraca liczby nieparzyste
	{
		if(val % 2 == 0) return false;
		try { Thread.sleep(1000); } catch(java.lang.InterruptedException ex) { } 
		return true; //oczywiœcie to nieprawda ;)
	}

	@Override
	public StreamObserver<Number> countPrimeNumbers(StreamObserver<Report> responseObserver) 
	{
		return new MyStreamObserver<Number>(responseObserver);
	}

}

class MyStreamObserver<Number> implements StreamObserver<Number> 
{
	private int count = 0;
	private StreamObserver<Report> responseObserver;

	MyStreamObserver(StreamObserver<Report> responseObserver)
	{
		System.out.println("BEGIN countPrimeNumbers");
		this.responseObserver = responseObserver;
	}

	@Override
	public void onNext(Number number) 
	{
		System.out.println("Received number " + ((sr.grpc.gen.Number)number).getValue());
		count++;
	}

	@Override
	public void onError(Throwable t) {
		System.out.println("ON ERROR");
	}

	@Override
	public void onCompleted() {
		responseObserver.onNext(Report.newBuilder().setCount(count).build());
		responseObserver.onCompleted();
		System.out.println("END countPrimeNumbers");
	}
}
