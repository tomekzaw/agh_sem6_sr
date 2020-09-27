package SmartHome.server;

import SmartHome.*;
import com.zeroc.Ice.Current;

import java.util.Random;

public class CameraI implements Camera
{
	private static final long serialVersionUID = -2448962912780867770L;

	private boolean isRecording = false;
	private Mode mode = Mode.Progressive;

	private Random random = new Random();

	public CameraI() {
		System.out.println("CameraI instantiated");
	}

	@Override
	public synchronized void takePicture(Current __current) throws NoMemorySpaceError {
		if (random.nextBoolean()) {
			System.out.println("pstryk");
		} else {
			throw new NoMemorySpaceError();
		}
	}

	@Override
	public synchronized Mode getMode(Current __current) {
		return this.mode;
	}

	@Override
	public synchronized void setMode(Mode mode, Current __current) throws RecordingAlreadyStartedError {
		if (this.isRecording) {
			throw new RecordingAlreadyStartedError();
		}
		this.mode = mode;
	}

	@Override
	public synchronized boolean isRecording(Current __current) {
		return this.isRecording;
	}

	@Override
	public synchronized void startRecording(Current __current) throws RecordingAlreadyStartedError {
		if (this.isRecording) {
			throw new RecordingAlreadyStartedError();
		}
		this.isRecording = true;
	}

	@Override
	public synchronized void stopRecording(Current __current) throws RecordingNotStartedError {
		if (!this.isRecording) {
			throw new RecordingNotStartedError();
		}
		this.isRecording = false;
	}
}
