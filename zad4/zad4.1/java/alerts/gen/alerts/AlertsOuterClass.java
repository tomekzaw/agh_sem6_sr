// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: alerts.proto

package alerts;

public final class AlertsOuterClass {
  private AlertsOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Subscription_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Subscription_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Notification_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Notification_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014alerts.proto\" \n\014Subscription\022\020\n\010hashta" +
      "gs\030\001 \003(\t\"R\n\014Notification\022\017\n\007message\030\001 \001(" +
      "\t\022\020\n\010hashtags\030\002 \003(\t\022\037\n\nimportance\030\003 \001(\0162" +
      "\013.Importance*F\n\nImportance\022\013\n\007UNKNOWN\020\000\022" +
      "\007\n\003LOW\020\001\022\n\n\006MEDIUM\020\002\022\010\n\004HIGH\020\003\022\014\n\010CRITIC" +
      "AL\020\00427\n\006Alerts\022-\n\tsubscribe\022\r.Subscripti" +
      "on\032\r.Notification\"\0000\001B\n\n\006alertsP\001b\006proto" +
      "3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_Subscription_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Subscription_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Subscription_descriptor,
        new java.lang.String[] { "Hashtags", });
    internal_static_Notification_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_Notification_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Notification_descriptor,
        new java.lang.String[] { "Message", "Hashtags", "Importance", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
