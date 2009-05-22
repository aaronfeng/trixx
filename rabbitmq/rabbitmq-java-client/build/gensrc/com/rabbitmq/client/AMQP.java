package com.rabbitmq.client;

import java.io.IOException;
import java.util.Map;
import java.util.Date;

import com.rabbitmq.client.impl.AMQContentHeader;
import com.rabbitmq.client.impl.ContentHeaderPropertyWriter;
import com.rabbitmq.client.impl.ContentHeaderPropertyReader;
import com.rabbitmq.client.impl.LongString;

public interface AMQP
{
    public static class PROTOCOL {
        public static final int MAJOR = 8;
        public static final int MINOR = 0;
        public static final int PORT = 5672;
    }

    public static final int FRAME_METHOD = 1;
    public static final int FRAME_HEADER = 2;
    public static final int FRAME_BODY = 3;
    public static final int FRAME_OOB_METHOD = 4;
    public static final int FRAME_OOB_HEADER = 5;
    public static final int FRAME_OOB_BODY = 6;
    public static final int FRAME_TRACE = 7;
    public static final int FRAME_HEARTBEAT = 8;
    public static final int FRAME_MIN_SIZE = 4096;
    public static final int FRAME_END = 206;
    public static final int REPLY_SUCCESS = 200;
    public static final int NOT_DELIVERED = 310;
    public static final int CONTENT_TOO_LARGE = 311;
    public static final int NO_ROUTE = 312;
    public static final int NO_CONSUMERS = 313;
    public static final int ACCESS_REFUSED = 403;
    public static final int NOT_FOUND = 404;
    public static final int RESOURCE_LOCKED = 405;
    public static final int PRECONDITION_FAILED = 406;
    public static final int CONNECTION_FORCED = 320;
    public static final int INVALID_PATH = 402;
    public static final int FRAME_ERROR = 501;
    public static final int SYNTAX_ERROR = 502;
    public static final int COMMAND_INVALID = 503;
    public static final int CHANNEL_ERROR = 504;
    public static final int RESOURCE_ERROR = 506;
    public static final int NOT_ALLOWED = 530;
    public static final int NOT_IMPLEMENTED = 540;
    public static final int INTERNAL_ERROR = 541;

    public static class Connection {
        public interface Start extends Method {
            int getVersionMajor();
            int getVersionMinor();
            Map<java.lang.String,Object> getServerProperties();
            LongString getMechanisms();
            LongString getLocales();
        }
        public interface StartOk extends Method {
            Map<java.lang.String,Object> getClientProperties();
            java.lang.String getMechanism();
            LongString getResponse();
            java.lang.String getLocale();
        }
        public interface Secure extends Method {
            LongString getChallenge();
        }
        public interface SecureOk extends Method {
            LongString getResponse();
        }
        public interface Tune extends Method {
            int getChannelMax();
            int getFrameMax();
            int getHeartbeat();
        }
        public interface TuneOk extends Method {
            int getChannelMax();
            int getFrameMax();
            int getHeartbeat();
        }
        public interface Open extends Method {
            java.lang.String getVirtualHost();
            java.lang.String getCapabilities();
            boolean getInsist();
        }
        public interface OpenOk extends Method {
            java.lang.String getKnownHosts();
        }
        public interface Redirect extends Method {
            java.lang.String getHost();
            java.lang.String getKnownHosts();
        }
        public interface Close extends Method {
            int getReplyCode();
            java.lang.String getReplyText();
            int getClassId();
            int getMethodId();
        }
        public interface CloseOk extends Method {
        }
    }

    public static class Channel {
        public interface Open extends Method {
            java.lang.String getOutOfBand();
        }
        public interface OpenOk extends Method {
        }
        public interface Flow extends Method {
            boolean getActive();
        }
        public interface FlowOk extends Method {
            boolean getActive();
        }
        public interface Alert extends Method {
            int getReplyCode();
            java.lang.String getReplyText();
            Map<java.lang.String,Object> getDetails();
        }
        public interface Close extends Method {
            int getReplyCode();
            java.lang.String getReplyText();
            int getClassId();
            int getMethodId();
        }
        public interface CloseOk extends Method {
        }
    }

    public static class Access {
        public interface Request extends Method {
            java.lang.String getRealm();
            boolean getExclusive();
            boolean getPassive();
            boolean getActive();
            boolean getWrite();
            boolean getRead();
        }
        public interface RequestOk extends Method {
            int getTicket();
        }
    }

    public static class Exchange {
        public interface Declare extends Method {
            int getTicket();
            java.lang.String getExchange();
            java.lang.String getType();
            boolean getPassive();
            boolean getDurable();
            boolean getAutoDelete();
            boolean getInternal();
            boolean getNowait();
            Map<java.lang.String,Object> getArguments();
        }
        public interface DeclareOk extends Method {
        }
        public interface Delete extends Method {
            int getTicket();
            java.lang.String getExchange();
            boolean getIfUnused();
            boolean getNowait();
        }
        public interface DeleteOk extends Method {
        }
    }

    public static class Queue {
        public interface Declare extends Method {
            int getTicket();
            java.lang.String getQueue();
            boolean getPassive();
            boolean getDurable();
            boolean getExclusive();
            boolean getAutoDelete();
            boolean getNowait();
            Map<java.lang.String,Object> getArguments();
        }
        public interface DeclareOk extends Method {
            java.lang.String getQueue();
            int getMessageCount();
            int getConsumerCount();
        }
        public interface Bind extends Method {
            int getTicket();
            java.lang.String getQueue();
            java.lang.String getExchange();
            java.lang.String getRoutingKey();
            boolean getNowait();
            Map<java.lang.String,Object> getArguments();
        }
        public interface BindOk extends Method {
        }
        public interface Purge extends Method {
            int getTicket();
            java.lang.String getQueue();
            boolean getNowait();
        }
        public interface PurgeOk extends Method {
            int getMessageCount();
        }
        public interface Delete extends Method {
            int getTicket();
            java.lang.String getQueue();
            boolean getIfUnused();
            boolean getIfEmpty();
            boolean getNowait();
        }
        public interface DeleteOk extends Method {
            int getMessageCount();
        }
        public interface Unbind extends Method {
            int getTicket();
            java.lang.String getQueue();
            java.lang.String getExchange();
            java.lang.String getRoutingKey();
            Map<java.lang.String,Object> getArguments();
        }
        public interface UnbindOk extends Method {
        }
    }

    public static class Basic {
        public interface Qos extends Method {
            int getPrefetchSize();
            int getPrefetchCount();
            boolean getGlobal();
        }
        public interface QosOk extends Method {
        }
        public interface Consume extends Method {
            int getTicket();
            java.lang.String getQueue();
            java.lang.String getConsumerTag();
            boolean getNoLocal();
            boolean getNoAck();
            boolean getExclusive();
            boolean getNowait();
        }
        public interface ConsumeOk extends Method {
            java.lang.String getConsumerTag();
        }
        public interface Cancel extends Method {
            java.lang.String getConsumerTag();
            boolean getNowait();
        }
        public interface CancelOk extends Method {
            java.lang.String getConsumerTag();
        }
        public interface Publish extends Method {
            int getTicket();
            java.lang.String getExchange();
            java.lang.String getRoutingKey();
            boolean getMandatory();
            boolean getImmediate();
        }
        public interface Return extends Method {
            int getReplyCode();
            java.lang.String getReplyText();
            java.lang.String getExchange();
            java.lang.String getRoutingKey();
        }
        public interface Deliver extends Method {
            java.lang.String getConsumerTag();
            long getDeliveryTag();
            boolean getRedelivered();
            java.lang.String getExchange();
            java.lang.String getRoutingKey();
        }
        public interface Get extends Method {
            int getTicket();
            java.lang.String getQueue();
            boolean getNoAck();
        }
        public interface GetOk extends Method {
            long getDeliveryTag();
            boolean getRedelivered();
            java.lang.String getExchange();
            java.lang.String getRoutingKey();
            int getMessageCount();
        }
        public interface GetEmpty extends Method {
            java.lang.String getClusterId();
        }
        public interface Ack extends Method {
            long getDeliveryTag();
            boolean getMultiple();
        }
        public interface Reject extends Method {
            long getDeliveryTag();
            boolean getRequeue();
        }
        public interface Recover extends Method {
            boolean getRequeue();
        }
    }

    public static class File {
        public interface Qos extends Method {
            int getPrefetchSize();
            int getPrefetchCount();
            boolean getGlobal();
        }
        public interface QosOk extends Method {
        }
        public interface Consume extends Method {
            int getTicket();
            java.lang.String getQueue();
            java.lang.String getConsumerTag();
            boolean getNoLocal();
            boolean getNoAck();
            boolean getExclusive();
            boolean getNowait();
        }
        public interface ConsumeOk extends Method {
            java.lang.String getConsumerTag();
        }
        public interface Cancel extends Method {
            java.lang.String getConsumerTag();
            boolean getNowait();
        }
        public interface CancelOk extends Method {
            java.lang.String getConsumerTag();
        }
        public interface Open extends Method {
            java.lang.String getIdentifier();
            long getContentSize();
        }
        public interface OpenOk extends Method {
            long getStagedSize();
        }
        public interface Stage extends Method {
        }
        public interface Publish extends Method {
            int getTicket();
            java.lang.String getExchange();
            java.lang.String getRoutingKey();
            boolean getMandatory();
            boolean getImmediate();
            java.lang.String getIdentifier();
        }
        public interface Return extends Method {
            int getReplyCode();
            java.lang.String getReplyText();
            java.lang.String getExchange();
            java.lang.String getRoutingKey();
        }
        public interface Deliver extends Method {
            java.lang.String getConsumerTag();
            long getDeliveryTag();
            boolean getRedelivered();
            java.lang.String getExchange();
            java.lang.String getRoutingKey();
            java.lang.String getIdentifier();
        }
        public interface Ack extends Method {
            long getDeliveryTag();
            boolean getMultiple();
        }
        public interface Reject extends Method {
            long getDeliveryTag();
            boolean getRequeue();
        }
    }

    public static class Stream {
        public interface Qos extends Method {
            int getPrefetchSize();
            int getPrefetchCount();
            int getConsumeRate();
            boolean getGlobal();
        }
        public interface QosOk extends Method {
        }
        public interface Consume extends Method {
            int getTicket();
            java.lang.String getQueue();
            java.lang.String getConsumerTag();
            boolean getNoLocal();
            boolean getExclusive();
            boolean getNowait();
        }
        public interface ConsumeOk extends Method {
            java.lang.String getConsumerTag();
        }
        public interface Cancel extends Method {
            java.lang.String getConsumerTag();
            boolean getNowait();
        }
        public interface CancelOk extends Method {
            java.lang.String getConsumerTag();
        }
        public interface Publish extends Method {
            int getTicket();
            java.lang.String getExchange();
            java.lang.String getRoutingKey();
            boolean getMandatory();
            boolean getImmediate();
        }
        public interface Return extends Method {
            int getReplyCode();
            java.lang.String getReplyText();
            java.lang.String getExchange();
            java.lang.String getRoutingKey();
        }
        public interface Deliver extends Method {
            java.lang.String getConsumerTag();
            long getDeliveryTag();
            java.lang.String getExchange();
            java.lang.String getQueue();
        }
    }

    public static class Tx {
        public interface Select extends Method {
        }
        public interface SelectOk extends Method {
        }
        public interface Commit extends Method {
        }
        public interface CommitOk extends Method {
        }
        public interface Rollback extends Method {
        }
        public interface RollbackOk extends Method {
        }
    }

    public static class Dtx {
        public interface Select extends Method {
        }
        public interface SelectOk extends Method {
        }
        public interface Start extends Method {
            java.lang.String getDtxIdentifier();
        }
        public interface StartOk extends Method {
        }
    }

    public static class Tunnel {
        public interface Request extends Method {
            Map<java.lang.String,Object> getMetaData();
        }
    }

    public static class Test {
        public interface Integer extends Method {
            int getInteger1();
            int getInteger2();
            int getInteger3();
            long getInteger4();
            int getOperation();
        }
        public interface IntegerOk extends Method {
            long getResult();
        }
        public interface String extends Method {
            java.lang.String getString1();
            LongString getString2();
            int getOperation();
        }
        public interface StringOk extends Method {
            LongString getResult();
        }
        public interface Table extends Method {
            Map<java.lang.String,Object> getTable();
            int getIntegerOp();
            int getStringOp();
        }
        public interface TableOk extends Method {
            long getIntegerResult();
            LongString getStringResult();
        }
        public interface Content extends Method {
        }
        public interface ContentOk extends Method {
            int getContentChecksum();
        }
    }

    public static class BasicProperties extends AMQContentHeader {
        public java.lang.String contentType;
        public java.lang.String contentEncoding;
        public Map<java.lang.String,Object> headers;
        public java.lang.Integer deliveryMode;
        public java.lang.Integer priority;
        public java.lang.String correlationId;
        public java.lang.String replyTo;
        public java.lang.String expiration;
        public java.lang.String messageId;
        public Date timestamp;
        public java.lang.String type;
        public java.lang.String userId;
        public java.lang.String appId;
        public java.lang.String clusterId;

        public BasicProperties ( 
            java.lang.String contentType,
            java.lang.String contentEncoding,
            Map<java.lang.String,Object> headers,
            java.lang.Integer deliveryMode,
            java.lang.Integer priority,
            java.lang.String correlationId,
            java.lang.String replyTo,
            java.lang.String expiration,
            java.lang.String messageId,
            Date timestamp,
            java.lang.String type,
            java.lang.String userId,
            java.lang.String appId,
            java.lang.String clusterId)
        {
            this.contentType = contentType;
            this.contentEncoding = contentEncoding;
            this.headers = headers;
            this.deliveryMode = deliveryMode;
            this.priority = priority;
            this.correlationId = correlationId;
            this.replyTo = replyTo;
            this.expiration = expiration;
            this.messageId = messageId;
            this.timestamp = timestamp;
            this.type = type;
            this.userId = userId;
            this.appId = appId;
            this.clusterId = clusterId;
        }

        public BasicProperties() {}
        public int getClassId() { return 60; }
        public java.lang.String getClassName() { return "basic"; }

        public void readPropertiesFrom(ContentHeaderPropertyReader reader)
            throws IOException
        {
            boolean contentType_present = reader.readPresence();
            boolean contentEncoding_present = reader.readPresence();
            boolean headers_present = reader.readPresence();
            boolean deliveryMode_present = reader.readPresence();
            boolean priority_present = reader.readPresence();
            boolean correlationId_present = reader.readPresence();
            boolean replyTo_present = reader.readPresence();
            boolean expiration_present = reader.readPresence();
            boolean messageId_present = reader.readPresence();
            boolean timestamp_present = reader.readPresence();
            boolean type_present = reader.readPresence();
            boolean userId_present = reader.readPresence();
            boolean appId_present = reader.readPresence();
            boolean clusterId_present = reader.readPresence();
            reader.finishPresence();
            this.contentType = contentType_present ? reader.readShortstr() : null;
            this.contentEncoding = contentEncoding_present ? reader.readShortstr() : null;
            this.headers = headers_present ? reader.readTable() : null;
            this.deliveryMode = deliveryMode_present ? reader.readOctet() : null;
            this.priority = priority_present ? reader.readOctet() : null;
            this.correlationId = correlationId_present ? reader.readShortstr() : null;
            this.replyTo = replyTo_present ? reader.readShortstr() : null;
            this.expiration = expiration_present ? reader.readShortstr() : null;
            this.messageId = messageId_present ? reader.readShortstr() : null;
            this.timestamp = timestamp_present ? reader.readTimestamp() : null;
            this.type = type_present ? reader.readShortstr() : null;
            this.userId = userId_present ? reader.readShortstr() : null;
            this.appId = appId_present ? reader.readShortstr() : null;
            this.clusterId = clusterId_present ? reader.readShortstr() : null;
        }

        public void writePropertiesTo(ContentHeaderPropertyWriter writer)
            throws IOException
        {
            writer.writePresence(this.contentType != null);
            writer.writePresence(this.contentEncoding != null);
            writer.writePresence(this.headers != null);
            writer.writePresence(this.deliveryMode != null);
            writer.writePresence(this.priority != null);
            writer.writePresence(this.correlationId != null);
            writer.writePresence(this.replyTo != null);
            writer.writePresence(this.expiration != null);
            writer.writePresence(this.messageId != null);
            writer.writePresence(this.timestamp != null);
            writer.writePresence(this.type != null);
            writer.writePresence(this.userId != null);
            writer.writePresence(this.appId != null);
            writer.writePresence(this.clusterId != null);
            writer.finishPresence();
            if (this.contentType != null) { writer.writeShortstr(this.contentType); } 
            if (this.contentEncoding != null) { writer.writeShortstr(this.contentEncoding); } 
            if (this.headers != null) { writer.writeTable(this.headers); } 
            if (this.deliveryMode != null) { writer.writeOctet(this.deliveryMode); } 
            if (this.priority != null) { writer.writeOctet(this.priority); } 
            if (this.correlationId != null) { writer.writeShortstr(this.correlationId); } 
            if (this.replyTo != null) { writer.writeShortstr(this.replyTo); } 
            if (this.expiration != null) { writer.writeShortstr(this.expiration); } 
            if (this.messageId != null) { writer.writeShortstr(this.messageId); } 
            if (this.timestamp != null) { writer.writeTimestamp(this.timestamp); } 
            if (this.type != null) { writer.writeShortstr(this.type); } 
            if (this.userId != null) { writer.writeShortstr(this.userId); } 
            if (this.appId != null) { writer.writeShortstr(this.appId); } 
            if (this.clusterId != null) { writer.writeShortstr(this.clusterId); } 
        }

        public void appendPropertyDebugStringTo(StringBuffer acc) {
            acc.append("(");
            acc.append("content-type=");
            acc.append(this.contentType);
            acc.append(", ");
            acc.append("content-encoding=");
            acc.append(this.contentEncoding);
            acc.append(", ");
            acc.append("headers=");
            acc.append(this.headers);
            acc.append(", ");
            acc.append("delivery-mode=");
            acc.append(this.deliveryMode);
            acc.append(", ");
            acc.append("priority=");
            acc.append(this.priority);
            acc.append(", ");
            acc.append("correlation-id=");
            acc.append(this.correlationId);
            acc.append(", ");
            acc.append("reply-to=");
            acc.append(this.replyTo);
            acc.append(", ");
            acc.append("expiration=");
            acc.append(this.expiration);
            acc.append(", ");
            acc.append("message-id=");
            acc.append(this.messageId);
            acc.append(", ");
            acc.append("timestamp=");
            acc.append(this.timestamp);
            acc.append(", ");
            acc.append("type=");
            acc.append(this.type);
            acc.append(", ");
            acc.append("user-id=");
            acc.append(this.userId);
            acc.append(", ");
            acc.append("app-id=");
            acc.append(this.appId);
            acc.append(", ");
            acc.append("cluster-id=");
            acc.append(this.clusterId);
            acc.append(")");
        }
    }

    public static class FileProperties extends AMQContentHeader {
        public java.lang.String contentType;
        public java.lang.String contentEncoding;
        public Map<java.lang.String,Object> headers;
        public java.lang.Integer priority;
        public java.lang.String replyTo;
        public java.lang.String messageId;
        public java.lang.String filename;
        public Date timestamp;
        public java.lang.String clusterId;

        public FileProperties ( 
            java.lang.String contentType,
            java.lang.String contentEncoding,
            Map<java.lang.String,Object> headers,
            java.lang.Integer priority,
            java.lang.String replyTo,
            java.lang.String messageId,
            java.lang.String filename,
            Date timestamp,
            java.lang.String clusterId)
        {
            this.contentType = contentType;
            this.contentEncoding = contentEncoding;
            this.headers = headers;
            this.priority = priority;
            this.replyTo = replyTo;
            this.messageId = messageId;
            this.filename = filename;
            this.timestamp = timestamp;
            this.clusterId = clusterId;
        }

        public FileProperties() {}
        public int getClassId() { return 70; }
        public java.lang.String getClassName() { return "file"; }

        public void readPropertiesFrom(ContentHeaderPropertyReader reader)
            throws IOException
        {
            boolean contentType_present = reader.readPresence();
            boolean contentEncoding_present = reader.readPresence();
            boolean headers_present = reader.readPresence();
            boolean priority_present = reader.readPresence();
            boolean replyTo_present = reader.readPresence();
            boolean messageId_present = reader.readPresence();
            boolean filename_present = reader.readPresence();
            boolean timestamp_present = reader.readPresence();
            boolean clusterId_present = reader.readPresence();
            reader.finishPresence();
            this.contentType = contentType_present ? reader.readShortstr() : null;
            this.contentEncoding = contentEncoding_present ? reader.readShortstr() : null;
            this.headers = headers_present ? reader.readTable() : null;
            this.priority = priority_present ? reader.readOctet() : null;
            this.replyTo = replyTo_present ? reader.readShortstr() : null;
            this.messageId = messageId_present ? reader.readShortstr() : null;
            this.filename = filename_present ? reader.readShortstr() : null;
            this.timestamp = timestamp_present ? reader.readTimestamp() : null;
            this.clusterId = clusterId_present ? reader.readShortstr() : null;
        }

        public void writePropertiesTo(ContentHeaderPropertyWriter writer)
            throws IOException
        {
            writer.writePresence(this.contentType != null);
            writer.writePresence(this.contentEncoding != null);
            writer.writePresence(this.headers != null);
            writer.writePresence(this.priority != null);
            writer.writePresence(this.replyTo != null);
            writer.writePresence(this.messageId != null);
            writer.writePresence(this.filename != null);
            writer.writePresence(this.timestamp != null);
            writer.writePresence(this.clusterId != null);
            writer.finishPresence();
            if (this.contentType != null) { writer.writeShortstr(this.contentType); } 
            if (this.contentEncoding != null) { writer.writeShortstr(this.contentEncoding); } 
            if (this.headers != null) { writer.writeTable(this.headers); } 
            if (this.priority != null) { writer.writeOctet(this.priority); } 
            if (this.replyTo != null) { writer.writeShortstr(this.replyTo); } 
            if (this.messageId != null) { writer.writeShortstr(this.messageId); } 
            if (this.filename != null) { writer.writeShortstr(this.filename); } 
            if (this.timestamp != null) { writer.writeTimestamp(this.timestamp); } 
            if (this.clusterId != null) { writer.writeShortstr(this.clusterId); } 
        }

        public void appendPropertyDebugStringTo(StringBuffer acc) {
            acc.append("(");
            acc.append("content-type=");
            acc.append(this.contentType);
            acc.append(", ");
            acc.append("content-encoding=");
            acc.append(this.contentEncoding);
            acc.append(", ");
            acc.append("headers=");
            acc.append(this.headers);
            acc.append(", ");
            acc.append("priority=");
            acc.append(this.priority);
            acc.append(", ");
            acc.append("reply-to=");
            acc.append(this.replyTo);
            acc.append(", ");
            acc.append("message-id=");
            acc.append(this.messageId);
            acc.append(", ");
            acc.append("filename=");
            acc.append(this.filename);
            acc.append(", ");
            acc.append("timestamp=");
            acc.append(this.timestamp);
            acc.append(", ");
            acc.append("cluster-id=");
            acc.append(this.clusterId);
            acc.append(")");
        }
    }

    public static class StreamProperties extends AMQContentHeader {
        public java.lang.String contentType;
        public java.lang.String contentEncoding;
        public Map<java.lang.String,Object> headers;
        public java.lang.Integer priority;
        public Date timestamp;

        public StreamProperties ( 
            java.lang.String contentType,
            java.lang.String contentEncoding,
            Map<java.lang.String,Object> headers,
            java.lang.Integer priority,
            Date timestamp)
        {
            this.contentType = contentType;
            this.contentEncoding = contentEncoding;
            this.headers = headers;
            this.priority = priority;
            this.timestamp = timestamp;
        }

        public StreamProperties() {}
        public int getClassId() { return 80; }
        public java.lang.String getClassName() { return "stream"; }

        public void readPropertiesFrom(ContentHeaderPropertyReader reader)
            throws IOException
        {
            boolean contentType_present = reader.readPresence();
            boolean contentEncoding_present = reader.readPresence();
            boolean headers_present = reader.readPresence();
            boolean priority_present = reader.readPresence();
            boolean timestamp_present = reader.readPresence();
            reader.finishPresence();
            this.contentType = contentType_present ? reader.readShortstr() : null;
            this.contentEncoding = contentEncoding_present ? reader.readShortstr() : null;
            this.headers = headers_present ? reader.readTable() : null;
            this.priority = priority_present ? reader.readOctet() : null;
            this.timestamp = timestamp_present ? reader.readTimestamp() : null;
        }

        public void writePropertiesTo(ContentHeaderPropertyWriter writer)
            throws IOException
        {
            writer.writePresence(this.contentType != null);
            writer.writePresence(this.contentEncoding != null);
            writer.writePresence(this.headers != null);
            writer.writePresence(this.priority != null);
            writer.writePresence(this.timestamp != null);
            writer.finishPresence();
            if (this.contentType != null) { writer.writeShortstr(this.contentType); } 
            if (this.contentEncoding != null) { writer.writeShortstr(this.contentEncoding); } 
            if (this.headers != null) { writer.writeTable(this.headers); } 
            if (this.priority != null) { writer.writeOctet(this.priority); } 
            if (this.timestamp != null) { writer.writeTimestamp(this.timestamp); } 
        }

        public void appendPropertyDebugStringTo(StringBuffer acc) {
            acc.append("(");
            acc.append("content-type=");
            acc.append(this.contentType);
            acc.append(", ");
            acc.append("content-encoding=");
            acc.append(this.contentEncoding);
            acc.append(", ");
            acc.append("headers=");
            acc.append(this.headers);
            acc.append(", ");
            acc.append("priority=");
            acc.append(this.priority);
            acc.append(", ");
            acc.append("timestamp=");
            acc.append(this.timestamp);
            acc.append(")");
        }
    }

    public static class TunnelProperties extends AMQContentHeader {
        public Map<java.lang.String,Object> headers;
        public java.lang.String proxyName;
        public java.lang.String dataName;
        public java.lang.Integer durable;
        public java.lang.Integer broadcast;

        public TunnelProperties ( 
            Map<java.lang.String,Object> headers,
            java.lang.String proxyName,
            java.lang.String dataName,
            java.lang.Integer durable,
            java.lang.Integer broadcast)
        {
            this.headers = headers;
            this.proxyName = proxyName;
            this.dataName = dataName;
            this.durable = durable;
            this.broadcast = broadcast;
        }

        public TunnelProperties() {}
        public int getClassId() { return 110; }
        public java.lang.String getClassName() { return "tunnel"; }

        public void readPropertiesFrom(ContentHeaderPropertyReader reader)
            throws IOException
        {
            boolean headers_present = reader.readPresence();
            boolean proxyName_present = reader.readPresence();
            boolean dataName_present = reader.readPresence();
            boolean durable_present = reader.readPresence();
            boolean broadcast_present = reader.readPresence();
            reader.finishPresence();
            this.headers = headers_present ? reader.readTable() : null;
            this.proxyName = proxyName_present ? reader.readShortstr() : null;
            this.dataName = dataName_present ? reader.readShortstr() : null;
            this.durable = durable_present ? reader.readOctet() : null;
            this.broadcast = broadcast_present ? reader.readOctet() : null;
        }

        public void writePropertiesTo(ContentHeaderPropertyWriter writer)
            throws IOException
        {
            writer.writePresence(this.headers != null);
            writer.writePresence(this.proxyName != null);
            writer.writePresence(this.dataName != null);
            writer.writePresence(this.durable != null);
            writer.writePresence(this.broadcast != null);
            writer.finishPresence();
            if (this.headers != null) { writer.writeTable(this.headers); } 
            if (this.proxyName != null) { writer.writeShortstr(this.proxyName); } 
            if (this.dataName != null) { writer.writeShortstr(this.dataName); } 
            if (this.durable != null) { writer.writeOctet(this.durable); } 
            if (this.broadcast != null) { writer.writeOctet(this.broadcast); } 
        }

        public void appendPropertyDebugStringTo(StringBuffer acc) {
            acc.append("(");
            acc.append("headers=");
            acc.append(this.headers);
            acc.append(", ");
            acc.append("proxy-name=");
            acc.append(this.proxyName);
            acc.append(", ");
            acc.append("data-name=");
            acc.append(this.dataName);
            acc.append(", ");
            acc.append("durable=");
            acc.append(this.durable);
            acc.append(", ");
            acc.append("broadcast=");
            acc.append(this.broadcast);
            acc.append(")");
        }
    }

    public static class TestProperties extends AMQContentHeader {

        public TestProperties() {}
        public int getClassId() { return 120; }
        public java.lang.String getClassName() { return "test"; }

        public void readPropertiesFrom(ContentHeaderPropertyReader reader)
            throws IOException
        {
            reader.finishPresence();
        }

        public void writePropertiesTo(ContentHeaderPropertyWriter writer)
            throws IOException
        {
            writer.finishPresence();
        }

        public void appendPropertyDebugStringTo(StringBuffer acc) {
            acc.append("(");
            acc.append(")");
        }
    }
}
