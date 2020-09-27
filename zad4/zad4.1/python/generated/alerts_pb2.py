# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: alerts.proto

from google.protobuf.internal import enum_type_wrapper
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor.FileDescriptor(
  name='alerts.proto',
  package='',
  syntax='proto3',
  serialized_options=b'\n\006alertsP\001',
  serialized_pb=b'\n\x0c\x61lerts.proto\" \n\x0cSubscription\x12\x10\n\x08hashtags\x18\x01 \x03(\t\"R\n\x0cNotification\x12\x0f\n\x07message\x18\x01 \x01(\t\x12\x10\n\x08hashtags\x18\x02 \x03(\t\x12\x1f\n\nimportance\x18\x03 \x01(\x0e\x32\x0b.Importance*F\n\nImportance\x12\x0b\n\x07UNKNOWN\x10\x00\x12\x07\n\x03LOW\x10\x01\x12\n\n\x06MEDIUM\x10\x02\x12\x08\n\x04HIGH\x10\x03\x12\x0c\n\x08\x43RITICAL\x10\x04\x32\x37\n\x06\x41lerts\x12-\n\tsubscribe\x12\r.Subscription\x1a\r.Notification\"\x00\x30\x01\x42\n\n\x06\x61lertsP\x01\x62\x06proto3'
)

_IMPORTANCE = _descriptor.EnumDescriptor(
  name='Importance',
  full_name='Importance',
  filename=None,
  file=DESCRIPTOR,
  values=[
    _descriptor.EnumValueDescriptor(
      name='UNKNOWN', index=0, number=0,
      serialized_options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='LOW', index=1, number=1,
      serialized_options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MEDIUM', index=2, number=2,
      serialized_options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='HIGH', index=3, number=3,
      serialized_options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CRITICAL', index=4, number=4,
      serialized_options=None,
      type=None),
  ],
  containing_type=None,
  serialized_options=None,
  serialized_start=134,
  serialized_end=204,
)
_sym_db.RegisterEnumDescriptor(_IMPORTANCE)

Importance = enum_type_wrapper.EnumTypeWrapper(_IMPORTANCE)
UNKNOWN = 0
LOW = 1
MEDIUM = 2
HIGH = 3
CRITICAL = 4



_SUBSCRIPTION = _descriptor.Descriptor(
  name='Subscription',
  full_name='Subscription',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='hashtags', full_name='Subscription.hashtags', index=0,
      number=1, type=9, cpp_type=9, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=16,
  serialized_end=48,
)


_NOTIFICATION = _descriptor.Descriptor(
  name='Notification',
  full_name='Notification',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='message', full_name='Notification.message', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='hashtags', full_name='Notification.hashtags', index=1,
      number=2, type=9, cpp_type=9, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='importance', full_name='Notification.importance', index=2,
      number=3, type=14, cpp_type=8, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=50,
  serialized_end=132,
)

_NOTIFICATION.fields_by_name['importance'].enum_type = _IMPORTANCE
DESCRIPTOR.message_types_by_name['Subscription'] = _SUBSCRIPTION
DESCRIPTOR.message_types_by_name['Notification'] = _NOTIFICATION
DESCRIPTOR.enum_types_by_name['Importance'] = _IMPORTANCE
_sym_db.RegisterFileDescriptor(DESCRIPTOR)

Subscription = _reflection.GeneratedProtocolMessageType('Subscription', (_message.Message,), {
  'DESCRIPTOR' : _SUBSCRIPTION,
  '__module__' : 'alerts_pb2'
  # @@protoc_insertion_point(class_scope:Subscription)
  })
_sym_db.RegisterMessage(Subscription)

Notification = _reflection.GeneratedProtocolMessageType('Notification', (_message.Message,), {
  'DESCRIPTOR' : _NOTIFICATION,
  '__module__' : 'alerts_pb2'
  # @@protoc_insertion_point(class_scope:Notification)
  })
_sym_db.RegisterMessage(Notification)


DESCRIPTOR._options = None

_ALERTS = _descriptor.ServiceDescriptor(
  name='Alerts',
  full_name='Alerts',
  file=DESCRIPTOR,
  index=0,
  serialized_options=None,
  serialized_start=206,
  serialized_end=261,
  methods=[
  _descriptor.MethodDescriptor(
    name='subscribe',
    full_name='Alerts.subscribe',
    index=0,
    containing_service=None,
    input_type=_SUBSCRIPTION,
    output_type=_NOTIFICATION,
    serialized_options=None,
  ),
])
_sym_db.RegisterServiceDescriptor(_ALERTS)

DESCRIPTOR.services_by_name['Alerts'] = _ALERTS

# @@protoc_insertion_point(module_scope)