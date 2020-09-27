package SmartHome.server;

import SmartHome.*;
import com.zeroc.Ice.Current;

public class PTZCameraI extends CameraI implements PTZCamera
{
	private static final long serialVersionUID = -2448962912780867770L;

	private short pan = 0, tilt = 0, zoom = 0;

	public PTZCameraI() {
		System.out.println("PTZCameraI instantiated");
	}

	public PTZCameraI(short pan, short tilt, short zoom) {
		super();
		this.pan = pan;
		this.tilt = tilt;
		this.zoom = zoom;
	}

	@Override
	public synchronized PanTiltZoom getPanTiltZoom(Current __current)
	{
		return new PanTiltZoom(this.pan, this.tilt, this.zoom);
	}

	@Override
	public synchronized void setPanTiltZoom(PanTiltZoom ptz, Current __current) throws InvalidPanTiltZoomError {
		if (ptz.hasPan()) {
			short pan = ptz.getPan();
			if (pan < -180 || pan > 180) {
				throw new InvalidPanTiltZoomError();
			}
			this.pan = pan;
		}

		if (ptz.hasTilt()) {
			short tilt = ptz.getTilt();
			if (tilt < -90 || tilt > 90) {
				throw new InvalidPanTiltZoomError();
			}
			this.tilt = tilt;
		}

		if (ptz.hasZoom()) {
			short zoom = ptz.getZoom();
			if (zoom < 0 || zoom > 127) {
				throw new InvalidPanTiltZoomError();
			}
			this.zoom = zoom;
		}

		System.out.println("szz żżżż bzzz");
	}
}
