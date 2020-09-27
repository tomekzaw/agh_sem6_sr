package sr.grpc.server;

import sr.grpc.gen.ArithmeticOpResult;
import sr.grpc.gen.CalculatorGrpc.CalculatorImplBase;

public class CalculatorImpl extends CalculatorImplBase 
{
	@Override
	public void add(sr.grpc.gen.ArithmeticOpArguments request,
			io.grpc.stub.StreamObserver<sr.grpc.gen.ArithmeticOpResult> responseObserver) 
	{
		System.out.println("addRequest (" + request.getArg1() + ", " + request.getArg2() +")");
		int val = request.getArg1() + request.getArg2();
		ArithmeticOpResult result = ArithmeticOpResult.newBuilder().setRes(val).build();
		if(request.getArg1() > 100 && request.getArg2() > 100) try { Thread.sleep(1000); } catch(java.lang.InterruptedException ex) { }
		responseObserver.onNext(result);
		responseObserver.onCompleted();
	}

	@Override
	public void subtract(sr.grpc.gen.ArithmeticOpArguments request,
			io.grpc.stub.StreamObserver<sr.grpc.gen.ArithmeticOpResult> responseObserver) 
	{
		System.out.println("subtractRequest (" + request.getArg1() + ", " + request.getArg2() +")");
		int val = request.getArg1() - request.getArg2();
		ArithmeticOpResult result = ArithmeticOpResult.newBuilder().setRes(val).build();
		responseObserver.onNext(result);
		responseObserver.onCompleted();
	}

	@Override
	public void multiply(sr.grpc.gen.MultiplyArguments request,
						 io.grpc.stub.StreamObserver<sr.grpc.gen.ArithmeticOpResult> responseObserver)
	{
		System.out.println("multiplyRequest (" + request + ")");

		int res = 1;
		for (int i = 0; i < request.getNumbersCount(); i++) {
			res *= request.getNumbers(i);
		}

		ArithmeticOpResult result = ArithmeticOpResult.newBuilder().setRes(res).build();
		responseObserver.onNext(result);
		responseObserver.onCompleted();
	}




}
