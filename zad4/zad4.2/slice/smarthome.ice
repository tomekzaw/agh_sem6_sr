#ifndef SMART_HOME_ICE
#define SMART_HOME_ICE

module SmartHome
{
  interface TempSensor
  {
    idempotent float getTemperature();
  }
  
  exception NoMemorySpaceError {}
  exception RecordingAlreadyStartedError {}
  exception RecordingNotStartedError {}

  enum Mode { Progressive, Interlaced }

  interface Camera
  {
    void takePicture() throws NoMemorySpaceError;

    idempotent Mode getMode();
    idempotent void setMode(Mode mode) throws RecordingAlreadyStartedError;

    idempotent bool isRecording();
    idempotent void startRecording() throws NoMemorySpaceError, RecordingAlreadyStartedError;
    idempotent void stopRecording() throws NoMemorySpaceError, RecordingNotStartedError;
  }

  class PanTiltZoom
  {
    optional(0) short pan;
    optional(1) short tilt;
    optional(2) short zoom;
  }

  exception InvalidPanTiltZoomError {}

  interface PTZCamera extends Camera
  {
    idempotent PanTiltZoom getPanTiltZoom();
    idempotent void setPanTiltZoom(PanTiltZoom ptz) throws InvalidPanTiltZoomError;
  }

  interface Bulbulator
  {
	  void bulbul();
  }

  interface BulbulatorWithPrzyczlapka extends Bulbulator {
    void czlap();
  }
}

#endif
