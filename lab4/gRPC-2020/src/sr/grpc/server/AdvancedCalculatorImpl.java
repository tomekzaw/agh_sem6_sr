package sr.grpc.server;

import sr.grpc.gen.ComplexArithmeticOpResult;
import sr.grpc.gen.AdvancedCalculatorGrpc.AdvancedCalculatorImplBase;

public class AdvancedCalculatorImpl extends AdvancedCalculatorImplBase 
{
	@Override
	public void complexOperation(sr.grpc.gen.ComplexArithmeticOpArguments request,
			io.grpc.stub.StreamObserver<sr.grpc.gen.ComplexArithmeticOpResult> responseObserver) 
	{
		System.out.println("multipleArgumentsRequest (" + request.getOptypeValue() + ", #" + request.getArgsCount() +")");
		
		if(request.getArgsCount() == 0) {
			System.out.println("No agruments");
		}
		
		double res = 0;
		switch (request.getOptype()) {		
		case SUM:
			for (Double d : request.getArgsList()) res += d;
			break;
		case AVG:
			for (Double d : request.getArgsList()) res += d;
			res /= request.getArgsCount();
			break;
		case MIN:
			break;
		case MAX:
			break;
		case UNRECOGNIZED:
			break;
		}
		
		ComplexArithmeticOpResult result = ComplexArithmeticOpResult.newBuilder().setRes(res).build();
		responseObserver.onNext(result);
		responseObserver.onCompleted();
	}
}
