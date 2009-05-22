package com.rabbitmq.client.impl;

import java.io.IOException;
import java.io.DataInputStream;
import java.util.Map;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.UnknownClassOrMethodId;
import com.rabbitmq.client.UnexpectedMethodError;

public class AMQImpl implements AMQP
{

    public static class Connection {
        public static final int INDEX = 10;

        public static class Start
            extends Method
            implements com.rabbitmq.client.AMQP.Connection.Start
        {
            public static final int INDEX = 10;

            public int versionMajor;
            public int versionMinor;
            public Map<java.lang.String,Object> serverProperties;
            public LongString mechanisms;
            public LongString locales;

            public int getVersionMajor() { return versionMajor; }
            public int getVersionMinor() { return versionMinor; }
            public Map<java.lang.String,Object> getServerProperties() { return serverProperties; }
            public LongString getMechanisms() { return mechanisms; }
            public LongString getLocales() { return locales; }

            public Start(
                int versionMajor,
                int versionMinor,
                Map<java.lang.String,Object> serverProperties,
                LongString mechanisms,
                LongString locales)
            {
                this.versionMajor = versionMajor;
                this.versionMinor = versionMinor;
                this.serverProperties = serverProperties;
                this.mechanisms = mechanisms;
                this.locales = locales;
            }

            public Start() {}
            public int protocolClassId() { return 10; }
            public int protocolMethodId() { return 10; }
            public java.lang.String protocolMethodName() { return "connection.start";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("version-major=");
                acc.append(this.versionMajor);
                acc.append(",");
                acc.append("version-minor=");
                acc.append(this.versionMinor);
                acc.append(",");
                acc.append("server properties=");
                acc.append(this.serverProperties);
                acc.append(",");
                acc.append("mechanisms=");
                acc.append(this.mechanisms);
                acc.append(",");
                acc.append("locales=");
                acc.append(this.locales);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.versionMajor = reader.readOctet();
                this.versionMinor = reader.readOctet();
                this.serverProperties = reader.readTable();
                this.mechanisms = reader.readLongstr();
                this.locales = reader.readLongstr();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeOctet(this.versionMajor);
                writer.writeOctet(this.versionMinor);
                writer.writeTable(this.serverProperties);
                writer.writeLongstr(this.mechanisms);
                writer.writeLongstr(this.locales);
            }
        }

        public static class StartOk
            extends Method
            implements com.rabbitmq.client.AMQP.Connection.StartOk
        {
            public static final int INDEX = 11;

            public Map<java.lang.String,Object> clientProperties;
            public java.lang.String mechanism;
            public LongString response;
            public java.lang.String locale;

            public Map<java.lang.String,Object> getClientProperties() { return clientProperties; }
            public java.lang.String getMechanism() { return mechanism; }
            public LongString getResponse() { return response; }
            public java.lang.String getLocale() { return locale; }

            public StartOk(
                Map<java.lang.String,Object> clientProperties,
                java.lang.String mechanism,
                LongString response,
                java.lang.String locale)
            {
                this.clientProperties = clientProperties;
                this.mechanism = mechanism;
                this.response = response;
                this.locale = locale;
            }

            public StartOk() {}
            public int protocolClassId() { return 10; }
            public int protocolMethodId() { return 11; }
            public java.lang.String protocolMethodName() { return "connection.start-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("client-properties=");
                acc.append(this.clientProperties);
                acc.append(",");
                acc.append("mechanism=");
                acc.append(this.mechanism);
                acc.append(",");
                acc.append("response=");
                acc.append(this.response);
                acc.append(",");
                acc.append("locale=");
                acc.append(this.locale);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.clientProperties = reader.readTable();
                this.mechanism = reader.readShortstr();
                this.response = reader.readLongstr();
                this.locale = reader.readShortstr();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeTable(this.clientProperties);
                writer.writeShortstr(this.mechanism);
                writer.writeLongstr(this.response);
                writer.writeShortstr(this.locale);
            }
        }

        public static class Secure
            extends Method
            implements com.rabbitmq.client.AMQP.Connection.Secure
        {
            public static final int INDEX = 20;

            public LongString challenge;

            public LongString getChallenge() { return challenge; }

            public Secure(
                LongString challenge)
            {
                this.challenge = challenge;
            }

            public Secure() {}
            public int protocolClassId() { return 10; }
            public int protocolMethodId() { return 20; }
            public java.lang.String protocolMethodName() { return "connection.secure";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("challenge=");
                acc.append(this.challenge);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.challenge = reader.readLongstr();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeLongstr(this.challenge);
            }
        }

        public static class SecureOk
            extends Method
            implements com.rabbitmq.client.AMQP.Connection.SecureOk
        {
            public static final int INDEX = 21;

            public LongString response;

            public LongString getResponse() { return response; }

            public SecureOk(
                LongString response)
            {
                this.response = response;
            }

            public SecureOk() {}
            public int protocolClassId() { return 10; }
            public int protocolMethodId() { return 21; }
            public java.lang.String protocolMethodName() { return "connection.secure-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("response=");
                acc.append(this.response);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.response = reader.readLongstr();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeLongstr(this.response);
            }
        }

        public static class Tune
            extends Method
            implements com.rabbitmq.client.AMQP.Connection.Tune
        {
            public static final int INDEX = 30;

            public int channelMax;
            public int frameMax;
            public int heartbeat;

            public int getChannelMax() { return channelMax; }
            public int getFrameMax() { return frameMax; }
            public int getHeartbeat() { return heartbeat; }

            public Tune(
                int channelMax,
                int frameMax,
                int heartbeat)
            {
                this.channelMax = channelMax;
                this.frameMax = frameMax;
                this.heartbeat = heartbeat;
            }

            public Tune() {}
            public int protocolClassId() { return 10; }
            public int protocolMethodId() { return 30; }
            public java.lang.String protocolMethodName() { return "connection.tune";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("channel-max=");
                acc.append(this.channelMax);
                acc.append(",");
                acc.append("frame-max=");
                acc.append(this.frameMax);
                acc.append(",");
                acc.append("heartbeat=");
                acc.append(this.heartbeat);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.channelMax = reader.readShort();
                this.frameMax = reader.readLong();
                this.heartbeat = reader.readShort();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShort(this.channelMax);
                writer.writeLong(this.frameMax);
                writer.writeShort(this.heartbeat);
            }
        }

        public static class TuneOk
            extends Method
            implements com.rabbitmq.client.AMQP.Connection.TuneOk
        {
            public static final int INDEX = 31;

            public int channelMax;
            public int frameMax;
            public int heartbeat;

            public int getChannelMax() { return channelMax; }
            public int getFrameMax() { return frameMax; }
            public int getHeartbeat() { return heartbeat; }

            public TuneOk(
                int channelMax,
                int frameMax,
                int heartbeat)
            {
                this.channelMax = channelMax;
                this.frameMax = frameMax;
                this.heartbeat = heartbeat;
            }

            public TuneOk() {}
            public int protocolClassId() { return 10; }
            public int protocolMethodId() { return 31; }
            public java.lang.String protocolMethodName() { return "connection.tune-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("channel-max=");
                acc.append(this.channelMax);
                acc.append(",");
                acc.append("frame-max=");
                acc.append(this.frameMax);
                acc.append(",");
                acc.append("heartbeat=");
                acc.append(this.heartbeat);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.channelMax = reader.readShort();
                this.frameMax = reader.readLong();
                this.heartbeat = reader.readShort();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShort(this.channelMax);
                writer.writeLong(this.frameMax);
                writer.writeShort(this.heartbeat);
            }
        }

        public static class Open
            extends Method
            implements com.rabbitmq.client.AMQP.Connection.Open
        {
            public static final int INDEX = 40;

            public java.lang.String virtualHost;
            public java.lang.String capabilities;
            public boolean insist;

            public java.lang.String getVirtualHost() { return virtualHost; }
            public java.lang.String getCapabilities() { return capabilities; }
            public boolean getInsist() { return insist; }

            public Open(
                java.lang.String virtualHost,
                java.lang.String capabilities,
                boolean insist)
            {
                this.virtualHost = virtualHost;
                this.capabilities = capabilities;
                this.insist = insist;
            }

            public Open() {}
            public int protocolClassId() { return 10; }
            public int protocolMethodId() { return 40; }
            public java.lang.String protocolMethodName() { return "connection.open";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("virtual-host=");
                acc.append(this.virtualHost);
                acc.append(",");
                acc.append("capabilities=");
                acc.append(this.capabilities);
                acc.append(",");
                acc.append("insist=");
                acc.append(this.insist);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.virtualHost = reader.readShortstr();
                this.capabilities = reader.readShortstr();
                this.insist = reader.readBit();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShortstr(this.virtualHost);
                writer.writeShortstr(this.capabilities);
                writer.writeBit(this.insist);
            }
        }

        public static class OpenOk
            extends Method
            implements com.rabbitmq.client.AMQP.Connection.OpenOk
        {
            public static final int INDEX = 41;

            public java.lang.String knownHosts;

            public java.lang.String getKnownHosts() { return knownHosts; }

            public OpenOk(
                java.lang.String knownHosts)
            {
                this.knownHosts = knownHosts;
            }

            public OpenOk() {}
            public int protocolClassId() { return 10; }
            public int protocolMethodId() { return 41; }
            public java.lang.String protocolMethodName() { return "connection.open-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("known-hosts=");
                acc.append(this.knownHosts);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.knownHosts = reader.readShortstr();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShortstr(this.knownHosts);
            }
        }

        public static class Redirect
            extends Method
            implements com.rabbitmq.client.AMQP.Connection.Redirect
        {
            public static final int INDEX = 50;

            public java.lang.String host;
            public java.lang.String knownHosts;

            public java.lang.String getHost() { return host; }
            public java.lang.String getKnownHosts() { return knownHosts; }

            public Redirect(
                java.lang.String host,
                java.lang.String knownHosts)
            {
                this.host = host;
                this.knownHosts = knownHosts;
            }

            public Redirect() {}
            public int protocolClassId() { return 10; }
            public int protocolMethodId() { return 50; }
            public java.lang.String protocolMethodName() { return "connection.redirect";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("host=");
                acc.append(this.host);
                acc.append(",");
                acc.append("known-hosts=");
                acc.append(this.knownHosts);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.host = reader.readShortstr();
                this.knownHosts = reader.readShortstr();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShortstr(this.host);
                writer.writeShortstr(this.knownHosts);
            }
        }

        public static class Close
            extends Method
            implements com.rabbitmq.client.AMQP.Connection.Close
        {
            public static final int INDEX = 60;

            public int replyCode;
            public java.lang.String replyText;
            public int classId;
            public int methodId;

            public int getReplyCode() { return replyCode; }
            public java.lang.String getReplyText() { return replyText; }
            public int getClassId() { return classId; }
            public int getMethodId() { return methodId; }

            public Close(
                int replyCode,
                java.lang.String replyText,
                int classId,
                int methodId)
            {
                this.replyCode = replyCode;
                this.replyText = replyText;
                this.classId = classId;
                this.methodId = methodId;
            }

            public Close() {}
            public int protocolClassId() { return 10; }
            public int protocolMethodId() { return 60; }
            public java.lang.String protocolMethodName() { return "connection.close";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("reply-code=");
                acc.append(this.replyCode);
                acc.append(",");
                acc.append("reply-text=");
                acc.append(this.replyText);
                acc.append(",");
                acc.append("class-id=");
                acc.append(this.classId);
                acc.append(",");
                acc.append("method-id=");
                acc.append(this.methodId);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.replyCode = reader.readShort();
                this.replyText = reader.readShortstr();
                this.classId = reader.readShort();
                this.methodId = reader.readShort();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShort(this.replyCode);
                writer.writeShortstr(this.replyText);
                writer.writeShort(this.classId);
                writer.writeShort(this.methodId);
            }
        }

        public static class CloseOk
            extends Method
            implements com.rabbitmq.client.AMQP.Connection.CloseOk
        {
            public static final int INDEX = 61;

            public CloseOk() {}
            public int protocolClassId() { return 10; }
            public int protocolMethodId() { return 61; }
            public java.lang.String protocolMethodName() { return "connection.close-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
            }
        }
    }

    public static class Channel {
        public static final int INDEX = 20;

        public static class Open
            extends Method
            implements com.rabbitmq.client.AMQP.Channel.Open
        {
            public static final int INDEX = 10;

            public java.lang.String outOfBand;

            public java.lang.String getOutOfBand() { return outOfBand; }

            public Open(
                java.lang.String outOfBand)
            {
                this.outOfBand = outOfBand;
            }

            public Open() {}
            public int protocolClassId() { return 20; }
            public int protocolMethodId() { return 10; }
            public java.lang.String protocolMethodName() { return "channel.open";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("out-of-band=");
                acc.append(this.outOfBand);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.outOfBand = reader.readShortstr();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShortstr(this.outOfBand);
            }
        }

        public static class OpenOk
            extends Method
            implements com.rabbitmq.client.AMQP.Channel.OpenOk
        {
            public static final int INDEX = 11;

            public OpenOk() {}
            public int protocolClassId() { return 20; }
            public int protocolMethodId() { return 11; }
            public java.lang.String protocolMethodName() { return "channel.open-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
            }
        }

        public static class Flow
            extends Method
            implements com.rabbitmq.client.AMQP.Channel.Flow
        {
            public static final int INDEX = 20;

            public boolean active;

            public boolean getActive() { return active; }

            public Flow(
                boolean active)
            {
                this.active = active;
            }

            public Flow() {}
            public int protocolClassId() { return 20; }
            public int protocolMethodId() { return 20; }
            public java.lang.String protocolMethodName() { return "channel.flow";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("active=");
                acc.append(this.active);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.active = reader.readBit();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeBit(this.active);
            }
        }

        public static class FlowOk
            extends Method
            implements com.rabbitmq.client.AMQP.Channel.FlowOk
        {
            public static final int INDEX = 21;

            public boolean active;

            public boolean getActive() { return active; }

            public FlowOk(
                boolean active)
            {
                this.active = active;
            }

            public FlowOk() {}
            public int protocolClassId() { return 20; }
            public int protocolMethodId() { return 21; }
            public java.lang.String protocolMethodName() { return "channel.flow-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("active=");
                acc.append(this.active);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.active = reader.readBit();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeBit(this.active);
            }
        }

        public static class Alert
            extends Method
            implements com.rabbitmq.client.AMQP.Channel.Alert
        {
            public static final int INDEX = 30;

            public int replyCode;
            public java.lang.String replyText;
            public Map<java.lang.String,Object> details;

            public int getReplyCode() { return replyCode; }
            public java.lang.String getReplyText() { return replyText; }
            public Map<java.lang.String,Object> getDetails() { return details; }

            public Alert(
                int replyCode,
                java.lang.String replyText,
                Map<java.lang.String,Object> details)
            {
                this.replyCode = replyCode;
                this.replyText = replyText;
                this.details = details;
            }

            public Alert() {}
            public int protocolClassId() { return 20; }
            public int protocolMethodId() { return 30; }
            public java.lang.String protocolMethodName() { return "channel.alert";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("reply-code=");
                acc.append(this.replyCode);
                acc.append(",");
                acc.append("reply-text=");
                acc.append(this.replyText);
                acc.append(",");
                acc.append("details=");
                acc.append(this.details);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.replyCode = reader.readShort();
                this.replyText = reader.readShortstr();
                this.details = reader.readTable();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShort(this.replyCode);
                writer.writeShortstr(this.replyText);
                writer.writeTable(this.details);
            }
        }

        public static class Close
            extends Method
            implements com.rabbitmq.client.AMQP.Channel.Close
        {
            public static final int INDEX = 40;

            public int replyCode;
            public java.lang.String replyText;
            public int classId;
            public int methodId;

            public int getReplyCode() { return replyCode; }
            public java.lang.String getReplyText() { return replyText; }
            public int getClassId() { return classId; }
            public int getMethodId() { return methodId; }

            public Close(
                int replyCode,
                java.lang.String replyText,
                int classId,
                int methodId)
            {
                this.replyCode = replyCode;
                this.replyText = replyText;
                this.classId = classId;
                this.methodId = methodId;
            }

            public Close() {}
            public int protocolClassId() { return 20; }
            public int protocolMethodId() { return 40; }
            public java.lang.String protocolMethodName() { return "channel.close";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("reply-code=");
                acc.append(this.replyCode);
                acc.append(",");
                acc.append("reply-text=");
                acc.append(this.replyText);
                acc.append(",");
                acc.append("class-id=");
                acc.append(this.classId);
                acc.append(",");
                acc.append("method-id=");
                acc.append(this.methodId);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.replyCode = reader.readShort();
                this.replyText = reader.readShortstr();
                this.classId = reader.readShort();
                this.methodId = reader.readShort();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShort(this.replyCode);
                writer.writeShortstr(this.replyText);
                writer.writeShort(this.classId);
                writer.writeShort(this.methodId);
            }
        }

        public static class CloseOk
            extends Method
            implements com.rabbitmq.client.AMQP.Channel.CloseOk
        {
            public static final int INDEX = 41;

            public CloseOk() {}
            public int protocolClassId() { return 20; }
            public int protocolMethodId() { return 41; }
            public java.lang.String protocolMethodName() { return "channel.close-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
            }
        }
    }

    public static class Access {
        public static final int INDEX = 30;

        public static class Request
            extends Method
            implements com.rabbitmq.client.AMQP.Access.Request
        {
            public static final int INDEX = 10;

            public java.lang.String realm;
            public boolean exclusive;
            public boolean passive;
            public boolean active;
            public boolean write;
            public boolean read;

            public java.lang.String getRealm() { return realm; }
            public boolean getExclusive() { return exclusive; }
            public boolean getPassive() { return passive; }
            public boolean getActive() { return active; }
            public boolean getWrite() { return write; }
            public boolean getRead() { return read; }

            public Request(
                java.lang.String realm,
                boolean exclusive,
                boolean passive,
                boolean active,
                boolean write,
                boolean read)
            {
                this.realm = realm;
                this.exclusive = exclusive;
                this.passive = passive;
                this.active = active;
                this.write = write;
                this.read = read;
            }

            public Request() {}
            public int protocolClassId() { return 30; }
            public int protocolMethodId() { return 10; }
            public java.lang.String protocolMethodName() { return "access.request";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("realm=");
                acc.append(this.realm);
                acc.append(",");
                acc.append("exclusive=");
                acc.append(this.exclusive);
                acc.append(",");
                acc.append("passive=");
                acc.append(this.passive);
                acc.append(",");
                acc.append("active=");
                acc.append(this.active);
                acc.append(",");
                acc.append("write=");
                acc.append(this.write);
                acc.append(",");
                acc.append("read=");
                acc.append(this.read);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.realm = reader.readShortstr();
                this.exclusive = reader.readBit();
                this.passive = reader.readBit();
                this.active = reader.readBit();
                this.write = reader.readBit();
                this.read = reader.readBit();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShortstr(this.realm);
                writer.writeBit(this.exclusive);
                writer.writeBit(this.passive);
                writer.writeBit(this.active);
                writer.writeBit(this.write);
                writer.writeBit(this.read);
            }
        }

        public static class RequestOk
            extends Method
            implements com.rabbitmq.client.AMQP.Access.RequestOk
        {
            public static final int INDEX = 11;

            public int ticket;

            public int getTicket() { return ticket; }

            public RequestOk(
                int ticket)
            {
                this.ticket = ticket;
            }

            public RequestOk() {}
            public int protocolClassId() { return 30; }
            public int protocolMethodId() { return 11; }
            public java.lang.String protocolMethodName() { return "access.request-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("ticket=");
                acc.append(this.ticket);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.ticket = reader.readShort();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShort(this.ticket);
            }
        }
    }

    public static class Exchange {
        public static final int INDEX = 40;

        public static class Declare
            extends Method
            implements com.rabbitmq.client.AMQP.Exchange.Declare
        {
            public static final int INDEX = 10;

            public int ticket;
            public java.lang.String exchange;
            public java.lang.String type;
            public boolean passive;
            public boolean durable;
            public boolean autoDelete;
            public boolean internal;
            public boolean nowait;
            public Map<java.lang.String,Object> arguments;

            public int getTicket() { return ticket; }
            public java.lang.String getExchange() { return exchange; }
            public java.lang.String getType() { return type; }
            public boolean getPassive() { return passive; }
            public boolean getDurable() { return durable; }
            public boolean getAutoDelete() { return autoDelete; }
            public boolean getInternal() { return internal; }
            public boolean getNowait() { return nowait; }
            public Map<java.lang.String,Object> getArguments() { return arguments; }

            public Declare(
                int ticket,
                java.lang.String exchange,
                java.lang.String type,
                boolean passive,
                boolean durable,
                boolean autoDelete,
                boolean internal,
                boolean nowait,
                Map<java.lang.String,Object> arguments)
            {
                this.ticket = ticket;
                this.exchange = exchange;
                this.type = type;
                this.passive = passive;
                this.durable = durable;
                this.autoDelete = autoDelete;
                this.internal = internal;
                this.nowait = nowait;
                this.arguments = arguments;
            }

            public Declare() {}
            public int protocolClassId() { return 40; }
            public int protocolMethodId() { return 10; }
            public java.lang.String protocolMethodName() { return "exchange.declare";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("ticket=");
                acc.append(this.ticket);
                acc.append(",");
                acc.append("exchange=");
                acc.append(this.exchange);
                acc.append(",");
                acc.append("type=");
                acc.append(this.type);
                acc.append(",");
                acc.append("passive=");
                acc.append(this.passive);
                acc.append(",");
                acc.append("durable=");
                acc.append(this.durable);
                acc.append(",");
                acc.append("auto-delete=");
                acc.append(this.autoDelete);
                acc.append(",");
                acc.append("internal=");
                acc.append(this.internal);
                acc.append(",");
                acc.append("nowait=");
                acc.append(this.nowait);
                acc.append(",");
                acc.append("arguments=");
                acc.append(this.arguments);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.ticket = reader.readShort();
                this.exchange = reader.readShortstr();
                this.type = reader.readShortstr();
                this.passive = reader.readBit();
                this.durable = reader.readBit();
                this.autoDelete = reader.readBit();
                this.internal = reader.readBit();
                this.nowait = reader.readBit();
                this.arguments = reader.readTable();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShort(this.ticket);
                writer.writeShortstr(this.exchange);
                writer.writeShortstr(this.type);
                writer.writeBit(this.passive);
                writer.writeBit(this.durable);
                writer.writeBit(this.autoDelete);
                writer.writeBit(this.internal);
                writer.writeBit(this.nowait);
                writer.writeTable(this.arguments);
            }
        }

        public static class DeclareOk
            extends Method
            implements com.rabbitmq.client.AMQP.Exchange.DeclareOk
        {
            public static final int INDEX = 11;

            public DeclareOk() {}
            public int protocolClassId() { return 40; }
            public int protocolMethodId() { return 11; }
            public java.lang.String protocolMethodName() { return "exchange.declare-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
            }
        }

        public static class Delete
            extends Method
            implements com.rabbitmq.client.AMQP.Exchange.Delete
        {
            public static final int INDEX = 20;

            public int ticket;
            public java.lang.String exchange;
            public boolean ifUnused;
            public boolean nowait;

            public int getTicket() { return ticket; }
            public java.lang.String getExchange() { return exchange; }
            public boolean getIfUnused() { return ifUnused; }
            public boolean getNowait() { return nowait; }

            public Delete(
                int ticket,
                java.lang.String exchange,
                boolean ifUnused,
                boolean nowait)
            {
                this.ticket = ticket;
                this.exchange = exchange;
                this.ifUnused = ifUnused;
                this.nowait = nowait;
            }

            public Delete() {}
            public int protocolClassId() { return 40; }
            public int protocolMethodId() { return 20; }
            public java.lang.String protocolMethodName() { return "exchange.delete";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("ticket=");
                acc.append(this.ticket);
                acc.append(",");
                acc.append("exchange=");
                acc.append(this.exchange);
                acc.append(",");
                acc.append("if-unused=");
                acc.append(this.ifUnused);
                acc.append(",");
                acc.append("nowait=");
                acc.append(this.nowait);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.ticket = reader.readShort();
                this.exchange = reader.readShortstr();
                this.ifUnused = reader.readBit();
                this.nowait = reader.readBit();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShort(this.ticket);
                writer.writeShortstr(this.exchange);
                writer.writeBit(this.ifUnused);
                writer.writeBit(this.nowait);
            }
        }

        public static class DeleteOk
            extends Method
            implements com.rabbitmq.client.AMQP.Exchange.DeleteOk
        {
            public static final int INDEX = 21;

            public DeleteOk() {}
            public int protocolClassId() { return 40; }
            public int protocolMethodId() { return 21; }
            public java.lang.String protocolMethodName() { return "exchange.delete-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
            }
        }
    }

    public static class Queue {
        public static final int INDEX = 50;

        public static class Declare
            extends Method
            implements com.rabbitmq.client.AMQP.Queue.Declare
        {
            public static final int INDEX = 10;

            public int ticket;
            public java.lang.String queue;
            public boolean passive;
            public boolean durable;
            public boolean exclusive;
            public boolean autoDelete;
            public boolean nowait;
            public Map<java.lang.String,Object> arguments;

            public int getTicket() { return ticket; }
            public java.lang.String getQueue() { return queue; }
            public boolean getPassive() { return passive; }
            public boolean getDurable() { return durable; }
            public boolean getExclusive() { return exclusive; }
            public boolean getAutoDelete() { return autoDelete; }
            public boolean getNowait() { return nowait; }
            public Map<java.lang.String,Object> getArguments() { return arguments; }

            public Declare(
                int ticket,
                java.lang.String queue,
                boolean passive,
                boolean durable,
                boolean exclusive,
                boolean autoDelete,
                boolean nowait,
                Map<java.lang.String,Object> arguments)
            {
                this.ticket = ticket;
                this.queue = queue;
                this.passive = passive;
                this.durable = durable;
                this.exclusive = exclusive;
                this.autoDelete = autoDelete;
                this.nowait = nowait;
                this.arguments = arguments;
            }

            public Declare() {}
            public int protocolClassId() { return 50; }
            public int protocolMethodId() { return 10; }
            public java.lang.String protocolMethodName() { return "queue.declare";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("ticket=");
                acc.append(this.ticket);
                acc.append(",");
                acc.append("queue=");
                acc.append(this.queue);
                acc.append(",");
                acc.append("passive=");
                acc.append(this.passive);
                acc.append(",");
                acc.append("durable=");
                acc.append(this.durable);
                acc.append(",");
                acc.append("exclusive=");
                acc.append(this.exclusive);
                acc.append(",");
                acc.append("auto-delete=");
                acc.append(this.autoDelete);
                acc.append(",");
                acc.append("nowait=");
                acc.append(this.nowait);
                acc.append(",");
                acc.append("arguments=");
                acc.append(this.arguments);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.ticket = reader.readShort();
                this.queue = reader.readShortstr();
                this.passive = reader.readBit();
                this.durable = reader.readBit();
                this.exclusive = reader.readBit();
                this.autoDelete = reader.readBit();
                this.nowait = reader.readBit();
                this.arguments = reader.readTable();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShort(this.ticket);
                writer.writeShortstr(this.queue);
                writer.writeBit(this.passive);
                writer.writeBit(this.durable);
                writer.writeBit(this.exclusive);
                writer.writeBit(this.autoDelete);
                writer.writeBit(this.nowait);
                writer.writeTable(this.arguments);
            }
        }

        public static class DeclareOk
            extends Method
            implements com.rabbitmq.client.AMQP.Queue.DeclareOk
        {
            public static final int INDEX = 11;

            public java.lang.String queue;
            public int messageCount;
            public int consumerCount;

            public java.lang.String getQueue() { return queue; }
            public int getMessageCount() { return messageCount; }
            public int getConsumerCount() { return consumerCount; }

            public DeclareOk(
                java.lang.String queue,
                int messageCount,
                int consumerCount)
            {
                this.queue = queue;
                this.messageCount = messageCount;
                this.consumerCount = consumerCount;
            }

            public DeclareOk() {}
            public int protocolClassId() { return 50; }
            public int protocolMethodId() { return 11; }
            public java.lang.String protocolMethodName() { return "queue.declare-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("queue=");
                acc.append(this.queue);
                acc.append(",");
                acc.append("message-count=");
                acc.append(this.messageCount);
                acc.append(",");
                acc.append("consumer-count=");
                acc.append(this.consumerCount);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.queue = reader.readShortstr();
                this.messageCount = reader.readLong();
                this.consumerCount = reader.readLong();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShortstr(this.queue);
                writer.writeLong(this.messageCount);
                writer.writeLong(this.consumerCount);
            }
        }

        public static class Bind
            extends Method
            implements com.rabbitmq.client.AMQP.Queue.Bind
        {
            public static final int INDEX = 20;

            public int ticket;
            public java.lang.String queue;
            public java.lang.String exchange;
            public java.lang.String routingKey;
            public boolean nowait;
            public Map<java.lang.String,Object> arguments;

            public int getTicket() { return ticket; }
            public java.lang.String getQueue() { return queue; }
            public java.lang.String getExchange() { return exchange; }
            public java.lang.String getRoutingKey() { return routingKey; }
            public boolean getNowait() { return nowait; }
            public Map<java.lang.String,Object> getArguments() { return arguments; }

            public Bind(
                int ticket,
                java.lang.String queue,
                java.lang.String exchange,
                java.lang.String routingKey,
                boolean nowait,
                Map<java.lang.String,Object> arguments)
            {
                this.ticket = ticket;
                this.queue = queue;
                this.exchange = exchange;
                this.routingKey = routingKey;
                this.nowait = nowait;
                this.arguments = arguments;
            }

            public Bind() {}
            public int protocolClassId() { return 50; }
            public int protocolMethodId() { return 20; }
            public java.lang.String protocolMethodName() { return "queue.bind";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("ticket=");
                acc.append(this.ticket);
                acc.append(",");
                acc.append("queue=");
                acc.append(this.queue);
                acc.append(",");
                acc.append("exchange=");
                acc.append(this.exchange);
                acc.append(",");
                acc.append("routing-key=");
                acc.append(this.routingKey);
                acc.append(",");
                acc.append("nowait=");
                acc.append(this.nowait);
                acc.append(",");
                acc.append("arguments=");
                acc.append(this.arguments);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.ticket = reader.readShort();
                this.queue = reader.readShortstr();
                this.exchange = reader.readShortstr();
                this.routingKey = reader.readShortstr();
                this.nowait = reader.readBit();
                this.arguments = reader.readTable();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShort(this.ticket);
                writer.writeShortstr(this.queue);
                writer.writeShortstr(this.exchange);
                writer.writeShortstr(this.routingKey);
                writer.writeBit(this.nowait);
                writer.writeTable(this.arguments);
            }
        }

        public static class BindOk
            extends Method
            implements com.rabbitmq.client.AMQP.Queue.BindOk
        {
            public static final int INDEX = 21;

            public BindOk() {}
            public int protocolClassId() { return 50; }
            public int protocolMethodId() { return 21; }
            public java.lang.String protocolMethodName() { return "queue.bind-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
            }
        }

        public static class Purge
            extends Method
            implements com.rabbitmq.client.AMQP.Queue.Purge
        {
            public static final int INDEX = 30;

            public int ticket;
            public java.lang.String queue;
            public boolean nowait;

            public int getTicket() { return ticket; }
            public java.lang.String getQueue() { return queue; }
            public boolean getNowait() { return nowait; }

            public Purge(
                int ticket,
                java.lang.String queue,
                boolean nowait)
            {
                this.ticket = ticket;
                this.queue = queue;
                this.nowait = nowait;
            }

            public Purge() {}
            public int protocolClassId() { return 50; }
            public int protocolMethodId() { return 30; }
            public java.lang.String protocolMethodName() { return "queue.purge";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("ticket=");
                acc.append(this.ticket);
                acc.append(",");
                acc.append("queue=");
                acc.append(this.queue);
                acc.append(",");
                acc.append("nowait=");
                acc.append(this.nowait);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.ticket = reader.readShort();
                this.queue = reader.readShortstr();
                this.nowait = reader.readBit();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShort(this.ticket);
                writer.writeShortstr(this.queue);
                writer.writeBit(this.nowait);
            }
        }

        public static class PurgeOk
            extends Method
            implements com.rabbitmq.client.AMQP.Queue.PurgeOk
        {
            public static final int INDEX = 31;

            public int messageCount;

            public int getMessageCount() { return messageCount; }

            public PurgeOk(
                int messageCount)
            {
                this.messageCount = messageCount;
            }

            public PurgeOk() {}
            public int protocolClassId() { return 50; }
            public int protocolMethodId() { return 31; }
            public java.lang.String protocolMethodName() { return "queue.purge-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("message-count=");
                acc.append(this.messageCount);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.messageCount = reader.readLong();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeLong(this.messageCount);
            }
        }

        public static class Delete
            extends Method
            implements com.rabbitmq.client.AMQP.Queue.Delete
        {
            public static final int INDEX = 40;

            public int ticket;
            public java.lang.String queue;
            public boolean ifUnused;
            public boolean ifEmpty;
            public boolean nowait;

            public int getTicket() { return ticket; }
            public java.lang.String getQueue() { return queue; }
            public boolean getIfUnused() { return ifUnused; }
            public boolean getIfEmpty() { return ifEmpty; }
            public boolean getNowait() { return nowait; }

            public Delete(
                int ticket,
                java.lang.String queue,
                boolean ifUnused,
                boolean ifEmpty,
                boolean nowait)
            {
                this.ticket = ticket;
                this.queue = queue;
                this.ifUnused = ifUnused;
                this.ifEmpty = ifEmpty;
                this.nowait = nowait;
            }

            public Delete() {}
            public int protocolClassId() { return 50; }
            public int protocolMethodId() { return 40; }
            public java.lang.String protocolMethodName() { return "queue.delete";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("ticket=");
                acc.append(this.ticket);
                acc.append(",");
                acc.append("queue=");
                acc.append(this.queue);
                acc.append(",");
                acc.append("if-unused=");
                acc.append(this.ifUnused);
                acc.append(",");
                acc.append("if-empty=");
                acc.append(this.ifEmpty);
                acc.append(",");
                acc.append("nowait=");
                acc.append(this.nowait);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.ticket = reader.readShort();
                this.queue = reader.readShortstr();
                this.ifUnused = reader.readBit();
                this.ifEmpty = reader.readBit();
                this.nowait = reader.readBit();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShort(this.ticket);
                writer.writeShortstr(this.queue);
                writer.writeBit(this.ifUnused);
                writer.writeBit(this.ifEmpty);
                writer.writeBit(this.nowait);
            }
        }

        public static class DeleteOk
            extends Method
            implements com.rabbitmq.client.AMQP.Queue.DeleteOk
        {
            public static final int INDEX = 41;

            public int messageCount;

            public int getMessageCount() { return messageCount; }

            public DeleteOk(
                int messageCount)
            {
                this.messageCount = messageCount;
            }

            public DeleteOk() {}
            public int protocolClassId() { return 50; }
            public int protocolMethodId() { return 41; }
            public java.lang.String protocolMethodName() { return "queue.delete-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("message-count=");
                acc.append(this.messageCount);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.messageCount = reader.readLong();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeLong(this.messageCount);
            }
        }

        public static class Unbind
            extends Method
            implements com.rabbitmq.client.AMQP.Queue.Unbind
        {
            public static final int INDEX = 50;

            public int ticket;
            public java.lang.String queue;
            public java.lang.String exchange;
            public java.lang.String routingKey;
            public Map<java.lang.String,Object> arguments;

            public int getTicket() { return ticket; }
            public java.lang.String getQueue() { return queue; }
            public java.lang.String getExchange() { return exchange; }
            public java.lang.String getRoutingKey() { return routingKey; }
            public Map<java.lang.String,Object> getArguments() { return arguments; }

            public Unbind(
                int ticket,
                java.lang.String queue,
                java.lang.String exchange,
                java.lang.String routingKey,
                Map<java.lang.String,Object> arguments)
            {
                this.ticket = ticket;
                this.queue = queue;
                this.exchange = exchange;
                this.routingKey = routingKey;
                this.arguments = arguments;
            }

            public Unbind() {}
            public int protocolClassId() { return 50; }
            public int protocolMethodId() { return 50; }
            public java.lang.String protocolMethodName() { return "queue.unbind";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("ticket=");
                acc.append(this.ticket);
                acc.append(",");
                acc.append("queue=");
                acc.append(this.queue);
                acc.append(",");
                acc.append("exchange=");
                acc.append(this.exchange);
                acc.append(",");
                acc.append("routing-key=");
                acc.append(this.routingKey);
                acc.append(",");
                acc.append("arguments=");
                acc.append(this.arguments);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.ticket = reader.readShort();
                this.queue = reader.readShortstr();
                this.exchange = reader.readShortstr();
                this.routingKey = reader.readShortstr();
                this.arguments = reader.readTable();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShort(this.ticket);
                writer.writeShortstr(this.queue);
                writer.writeShortstr(this.exchange);
                writer.writeShortstr(this.routingKey);
                writer.writeTable(this.arguments);
            }
        }

        public static class UnbindOk
            extends Method
            implements com.rabbitmq.client.AMQP.Queue.UnbindOk
        {
            public static final int INDEX = 51;

            public UnbindOk() {}
            public int protocolClassId() { return 50; }
            public int protocolMethodId() { return 51; }
            public java.lang.String protocolMethodName() { return "queue.unbind-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
            }
        }
    }

    public static class Basic {
        public static final int INDEX = 60;

        public static class Qos
            extends Method
            implements com.rabbitmq.client.AMQP.Basic.Qos
        {
            public static final int INDEX = 10;

            public int prefetchSize;
            public int prefetchCount;
            public boolean global;

            public int getPrefetchSize() { return prefetchSize; }
            public int getPrefetchCount() { return prefetchCount; }
            public boolean getGlobal() { return global; }

            public Qos(
                int prefetchSize,
                int prefetchCount,
                boolean global)
            {
                this.prefetchSize = prefetchSize;
                this.prefetchCount = prefetchCount;
                this.global = global;
            }

            public Qos() {}
            public int protocolClassId() { return 60; }
            public int protocolMethodId() { return 10; }
            public java.lang.String protocolMethodName() { return "basic.qos";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("prefetch-size=");
                acc.append(this.prefetchSize);
                acc.append(",");
                acc.append("prefetch-count=");
                acc.append(this.prefetchCount);
                acc.append(",");
                acc.append("global=");
                acc.append(this.global);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.prefetchSize = reader.readLong();
                this.prefetchCount = reader.readShort();
                this.global = reader.readBit();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeLong(this.prefetchSize);
                writer.writeShort(this.prefetchCount);
                writer.writeBit(this.global);
            }
        }

        public static class QosOk
            extends Method
            implements com.rabbitmq.client.AMQP.Basic.QosOk
        {
            public static final int INDEX = 11;

            public QosOk() {}
            public int protocolClassId() { return 60; }
            public int protocolMethodId() { return 11; }
            public java.lang.String protocolMethodName() { return "basic.qos-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
            }
        }

        public static class Consume
            extends Method
            implements com.rabbitmq.client.AMQP.Basic.Consume
        {
            public static final int INDEX = 20;

            public int ticket;
            public java.lang.String queue;
            public java.lang.String consumerTag;
            public boolean noLocal;
            public boolean noAck;
            public boolean exclusive;
            public boolean nowait;

            public int getTicket() { return ticket; }
            public java.lang.String getQueue() { return queue; }
            public java.lang.String getConsumerTag() { return consumerTag; }
            public boolean getNoLocal() { return noLocal; }
            public boolean getNoAck() { return noAck; }
            public boolean getExclusive() { return exclusive; }
            public boolean getNowait() { return nowait; }

            public Consume(
                int ticket,
                java.lang.String queue,
                java.lang.String consumerTag,
                boolean noLocal,
                boolean noAck,
                boolean exclusive,
                boolean nowait)
            {
                this.ticket = ticket;
                this.queue = queue;
                this.consumerTag = consumerTag;
                this.noLocal = noLocal;
                this.noAck = noAck;
                this.exclusive = exclusive;
                this.nowait = nowait;
            }

            public Consume() {}
            public int protocolClassId() { return 60; }
            public int protocolMethodId() { return 20; }
            public java.lang.String protocolMethodName() { return "basic.consume";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("ticket=");
                acc.append(this.ticket);
                acc.append(",");
                acc.append("queue=");
                acc.append(this.queue);
                acc.append(",");
                acc.append("consumer-tag=");
                acc.append(this.consumerTag);
                acc.append(",");
                acc.append("no-local=");
                acc.append(this.noLocal);
                acc.append(",");
                acc.append("no-ack=");
                acc.append(this.noAck);
                acc.append(",");
                acc.append("exclusive=");
                acc.append(this.exclusive);
                acc.append(",");
                acc.append("nowait=");
                acc.append(this.nowait);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.ticket = reader.readShort();
                this.queue = reader.readShortstr();
                this.consumerTag = reader.readShortstr();
                this.noLocal = reader.readBit();
                this.noAck = reader.readBit();
                this.exclusive = reader.readBit();
                this.nowait = reader.readBit();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShort(this.ticket);
                writer.writeShortstr(this.queue);
                writer.writeShortstr(this.consumerTag);
                writer.writeBit(this.noLocal);
                writer.writeBit(this.noAck);
                writer.writeBit(this.exclusive);
                writer.writeBit(this.nowait);
            }
        }

        public static class ConsumeOk
            extends Method
            implements com.rabbitmq.client.AMQP.Basic.ConsumeOk
        {
            public static final int INDEX = 21;

            public java.lang.String consumerTag;

            public java.lang.String getConsumerTag() { return consumerTag; }

            public ConsumeOk(
                java.lang.String consumerTag)
            {
                this.consumerTag = consumerTag;
            }

            public ConsumeOk() {}
            public int protocolClassId() { return 60; }
            public int protocolMethodId() { return 21; }
            public java.lang.String protocolMethodName() { return "basic.consume-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("consumer-tag=");
                acc.append(this.consumerTag);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.consumerTag = reader.readShortstr();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShortstr(this.consumerTag);
            }
        }

        public static class Cancel
            extends Method
            implements com.rabbitmq.client.AMQP.Basic.Cancel
        {
            public static final int INDEX = 30;

            public java.lang.String consumerTag;
            public boolean nowait;

            public java.lang.String getConsumerTag() { return consumerTag; }
            public boolean getNowait() { return nowait; }

            public Cancel(
                java.lang.String consumerTag,
                boolean nowait)
            {
                this.consumerTag = consumerTag;
                this.nowait = nowait;
            }

            public Cancel() {}
            public int protocolClassId() { return 60; }
            public int protocolMethodId() { return 30; }
            public java.lang.String protocolMethodName() { return "basic.cancel";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("consumer-tag=");
                acc.append(this.consumerTag);
                acc.append(",");
                acc.append("nowait=");
                acc.append(this.nowait);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.consumerTag = reader.readShortstr();
                this.nowait = reader.readBit();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShortstr(this.consumerTag);
                writer.writeBit(this.nowait);
            }
        }

        public static class CancelOk
            extends Method
            implements com.rabbitmq.client.AMQP.Basic.CancelOk
        {
            public static final int INDEX = 31;

            public java.lang.String consumerTag;

            public java.lang.String getConsumerTag() { return consumerTag; }

            public CancelOk(
                java.lang.String consumerTag)
            {
                this.consumerTag = consumerTag;
            }

            public CancelOk() {}
            public int protocolClassId() { return 60; }
            public int protocolMethodId() { return 31; }
            public java.lang.String protocolMethodName() { return "basic.cancel-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("consumer-tag=");
                acc.append(this.consumerTag);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.consumerTag = reader.readShortstr();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShortstr(this.consumerTag);
            }
        }

        public static class Publish
            extends Method
            implements com.rabbitmq.client.AMQP.Basic.Publish
        {
            public static final int INDEX = 40;

            public int ticket;
            public java.lang.String exchange;
            public java.lang.String routingKey;
            public boolean mandatory;
            public boolean immediate;

            public int getTicket() { return ticket; }
            public java.lang.String getExchange() { return exchange; }
            public java.lang.String getRoutingKey() { return routingKey; }
            public boolean getMandatory() { return mandatory; }
            public boolean getImmediate() { return immediate; }

            public Publish(
                int ticket,
                java.lang.String exchange,
                java.lang.String routingKey,
                boolean mandatory,
                boolean immediate)
            {
                this.ticket = ticket;
                this.exchange = exchange;
                this.routingKey = routingKey;
                this.mandatory = mandatory;
                this.immediate = immediate;
            }

            public Publish() {}
            public int protocolClassId() { return 60; }
            public int protocolMethodId() { return 40; }
            public java.lang.String protocolMethodName() { return "basic.publish";}

            public boolean hasContent() {
                return true;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("ticket=");
                acc.append(this.ticket);
                acc.append(",");
                acc.append("exchange=");
                acc.append(this.exchange);
                acc.append(",");
                acc.append("routing-key=");
                acc.append(this.routingKey);
                acc.append(",");
                acc.append("mandatory=");
                acc.append(this.mandatory);
                acc.append(",");
                acc.append("immediate=");
                acc.append(this.immediate);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.ticket = reader.readShort();
                this.exchange = reader.readShortstr();
                this.routingKey = reader.readShortstr();
                this.mandatory = reader.readBit();
                this.immediate = reader.readBit();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShort(this.ticket);
                writer.writeShortstr(this.exchange);
                writer.writeShortstr(this.routingKey);
                writer.writeBit(this.mandatory);
                writer.writeBit(this.immediate);
            }
        }

        public static class Return
            extends Method
            implements com.rabbitmq.client.AMQP.Basic.Return
        {
            public static final int INDEX = 50;

            public int replyCode;
            public java.lang.String replyText;
            public java.lang.String exchange;
            public java.lang.String routingKey;

            public int getReplyCode() { return replyCode; }
            public java.lang.String getReplyText() { return replyText; }
            public java.lang.String getExchange() { return exchange; }
            public java.lang.String getRoutingKey() { return routingKey; }

            public Return(
                int replyCode,
                java.lang.String replyText,
                java.lang.String exchange,
                java.lang.String routingKey)
            {
                this.replyCode = replyCode;
                this.replyText = replyText;
                this.exchange = exchange;
                this.routingKey = routingKey;
            }

            public Return() {}
            public int protocolClassId() { return 60; }
            public int protocolMethodId() { return 50; }
            public java.lang.String protocolMethodName() { return "basic.return";}

            public boolean hasContent() {
                return true;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("reply-code=");
                acc.append(this.replyCode);
                acc.append(",");
                acc.append("reply-text=");
                acc.append(this.replyText);
                acc.append(",");
                acc.append("exchange=");
                acc.append(this.exchange);
                acc.append(",");
                acc.append("routing-key=");
                acc.append(this.routingKey);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.replyCode = reader.readShort();
                this.replyText = reader.readShortstr();
                this.exchange = reader.readShortstr();
                this.routingKey = reader.readShortstr();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShort(this.replyCode);
                writer.writeShortstr(this.replyText);
                writer.writeShortstr(this.exchange);
                writer.writeShortstr(this.routingKey);
            }
        }

        public static class Deliver
            extends Method
            implements com.rabbitmq.client.AMQP.Basic.Deliver
        {
            public static final int INDEX = 60;

            public java.lang.String consumerTag;
            public long deliveryTag;
            public boolean redelivered;
            public java.lang.String exchange;
            public java.lang.String routingKey;

            public java.lang.String getConsumerTag() { return consumerTag; }
            public long getDeliveryTag() { return deliveryTag; }
            public boolean getRedelivered() { return redelivered; }
            public java.lang.String getExchange() { return exchange; }
            public java.lang.String getRoutingKey() { return routingKey; }

            public Deliver(
                java.lang.String consumerTag,
                long deliveryTag,
                boolean redelivered,
                java.lang.String exchange,
                java.lang.String routingKey)
            {
                this.consumerTag = consumerTag;
                this.deliveryTag = deliveryTag;
                this.redelivered = redelivered;
                this.exchange = exchange;
                this.routingKey = routingKey;
            }

            public Deliver() {}
            public int protocolClassId() { return 60; }
            public int protocolMethodId() { return 60; }
            public java.lang.String protocolMethodName() { return "basic.deliver";}

            public boolean hasContent() {
                return true;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("consumer-tag=");
                acc.append(this.consumerTag);
                acc.append(",");
                acc.append("delivery-tag=");
                acc.append(this.deliveryTag);
                acc.append(",");
                acc.append("redelivered=");
                acc.append(this.redelivered);
                acc.append(",");
                acc.append("exchange=");
                acc.append(this.exchange);
                acc.append(",");
                acc.append("routing-key=");
                acc.append(this.routingKey);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.consumerTag = reader.readShortstr();
                this.deliveryTag = reader.readLonglong();
                this.redelivered = reader.readBit();
                this.exchange = reader.readShortstr();
                this.routingKey = reader.readShortstr();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShortstr(this.consumerTag);
                writer.writeLonglong(this.deliveryTag);
                writer.writeBit(this.redelivered);
                writer.writeShortstr(this.exchange);
                writer.writeShortstr(this.routingKey);
            }
        }

        public static class Get
            extends Method
            implements com.rabbitmq.client.AMQP.Basic.Get
        {
            public static final int INDEX = 70;

            public int ticket;
            public java.lang.String queue;
            public boolean noAck;

            public int getTicket() { return ticket; }
            public java.lang.String getQueue() { return queue; }
            public boolean getNoAck() { return noAck; }

            public Get(
                int ticket,
                java.lang.String queue,
                boolean noAck)
            {
                this.ticket = ticket;
                this.queue = queue;
                this.noAck = noAck;
            }

            public Get() {}
            public int protocolClassId() { return 60; }
            public int protocolMethodId() { return 70; }
            public java.lang.String protocolMethodName() { return "basic.get";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("ticket=");
                acc.append(this.ticket);
                acc.append(",");
                acc.append("queue=");
                acc.append(this.queue);
                acc.append(",");
                acc.append("no-ack=");
                acc.append(this.noAck);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.ticket = reader.readShort();
                this.queue = reader.readShortstr();
                this.noAck = reader.readBit();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShort(this.ticket);
                writer.writeShortstr(this.queue);
                writer.writeBit(this.noAck);
            }
        }

        public static class GetOk
            extends Method
            implements com.rabbitmq.client.AMQP.Basic.GetOk
        {
            public static final int INDEX = 71;

            public long deliveryTag;
            public boolean redelivered;
            public java.lang.String exchange;
            public java.lang.String routingKey;
            public int messageCount;

            public long getDeliveryTag() { return deliveryTag; }
            public boolean getRedelivered() { return redelivered; }
            public java.lang.String getExchange() { return exchange; }
            public java.lang.String getRoutingKey() { return routingKey; }
            public int getMessageCount() { return messageCount; }

            public GetOk(
                long deliveryTag,
                boolean redelivered,
                java.lang.String exchange,
                java.lang.String routingKey,
                int messageCount)
            {
                this.deliveryTag = deliveryTag;
                this.redelivered = redelivered;
                this.exchange = exchange;
                this.routingKey = routingKey;
                this.messageCount = messageCount;
            }

            public GetOk() {}
            public int protocolClassId() { return 60; }
            public int protocolMethodId() { return 71; }
            public java.lang.String protocolMethodName() { return "basic.get-ok";}

            public boolean hasContent() {
                return true;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("delivery-tag=");
                acc.append(this.deliveryTag);
                acc.append(",");
                acc.append("redelivered=");
                acc.append(this.redelivered);
                acc.append(",");
                acc.append("exchange=");
                acc.append(this.exchange);
                acc.append(",");
                acc.append("routing-key=");
                acc.append(this.routingKey);
                acc.append(",");
                acc.append("message-count=");
                acc.append(this.messageCount);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.deliveryTag = reader.readLonglong();
                this.redelivered = reader.readBit();
                this.exchange = reader.readShortstr();
                this.routingKey = reader.readShortstr();
                this.messageCount = reader.readLong();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeLonglong(this.deliveryTag);
                writer.writeBit(this.redelivered);
                writer.writeShortstr(this.exchange);
                writer.writeShortstr(this.routingKey);
                writer.writeLong(this.messageCount);
            }
        }

        public static class GetEmpty
            extends Method
            implements com.rabbitmq.client.AMQP.Basic.GetEmpty
        {
            public static final int INDEX = 72;

            public java.lang.String clusterId;

            public java.lang.String getClusterId() { return clusterId; }

            public GetEmpty(
                java.lang.String clusterId)
            {
                this.clusterId = clusterId;
            }

            public GetEmpty() {}
            public int protocolClassId() { return 60; }
            public int protocolMethodId() { return 72; }
            public java.lang.String protocolMethodName() { return "basic.get-empty";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("cluster-id=");
                acc.append(this.clusterId);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.clusterId = reader.readShortstr();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShortstr(this.clusterId);
            }
        }

        public static class Ack
            extends Method
            implements com.rabbitmq.client.AMQP.Basic.Ack
        {
            public static final int INDEX = 80;

            public long deliveryTag;
            public boolean multiple;

            public long getDeliveryTag() { return deliveryTag; }
            public boolean getMultiple() { return multiple; }

            public Ack(
                long deliveryTag,
                boolean multiple)
            {
                this.deliveryTag = deliveryTag;
                this.multiple = multiple;
            }

            public Ack() {}
            public int protocolClassId() { return 60; }
            public int protocolMethodId() { return 80; }
            public java.lang.String protocolMethodName() { return "basic.ack";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("delivery-tag=");
                acc.append(this.deliveryTag);
                acc.append(",");
                acc.append("multiple=");
                acc.append(this.multiple);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.deliveryTag = reader.readLonglong();
                this.multiple = reader.readBit();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeLonglong(this.deliveryTag);
                writer.writeBit(this.multiple);
            }
        }

        public static class Reject
            extends Method
            implements com.rabbitmq.client.AMQP.Basic.Reject
        {
            public static final int INDEX = 90;

            public long deliveryTag;
            public boolean requeue;

            public long getDeliveryTag() { return deliveryTag; }
            public boolean getRequeue() { return requeue; }

            public Reject(
                long deliveryTag,
                boolean requeue)
            {
                this.deliveryTag = deliveryTag;
                this.requeue = requeue;
            }

            public Reject() {}
            public int protocolClassId() { return 60; }
            public int protocolMethodId() { return 90; }
            public java.lang.String protocolMethodName() { return "basic.reject";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("delivery-tag=");
                acc.append(this.deliveryTag);
                acc.append(",");
                acc.append("requeue=");
                acc.append(this.requeue);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.deliveryTag = reader.readLonglong();
                this.requeue = reader.readBit();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeLonglong(this.deliveryTag);
                writer.writeBit(this.requeue);
            }
        }

        public static class Recover
            extends Method
            implements com.rabbitmq.client.AMQP.Basic.Recover
        {
            public static final int INDEX = 100;

            public boolean requeue;

            public boolean getRequeue() { return requeue; }

            public Recover(
                boolean requeue)
            {
                this.requeue = requeue;
            }

            public Recover() {}
            public int protocolClassId() { return 60; }
            public int protocolMethodId() { return 100; }
            public java.lang.String protocolMethodName() { return "basic.recover";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("requeue=");
                acc.append(this.requeue);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.requeue = reader.readBit();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeBit(this.requeue);
            }
        }
    }

    public static class File {
        public static final int INDEX = 70;

        public static class Qos
            extends Method
            implements com.rabbitmq.client.AMQP.File.Qos
        {
            public static final int INDEX = 10;

            public int prefetchSize;
            public int prefetchCount;
            public boolean global;

            public int getPrefetchSize() { return prefetchSize; }
            public int getPrefetchCount() { return prefetchCount; }
            public boolean getGlobal() { return global; }

            public Qos(
                int prefetchSize,
                int prefetchCount,
                boolean global)
            {
                this.prefetchSize = prefetchSize;
                this.prefetchCount = prefetchCount;
                this.global = global;
            }

            public Qos() {}
            public int protocolClassId() { return 70; }
            public int protocolMethodId() { return 10; }
            public java.lang.String protocolMethodName() { return "file.qos";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("prefetch-size=");
                acc.append(this.prefetchSize);
                acc.append(",");
                acc.append("prefetch-count=");
                acc.append(this.prefetchCount);
                acc.append(",");
                acc.append("global=");
                acc.append(this.global);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.prefetchSize = reader.readLong();
                this.prefetchCount = reader.readShort();
                this.global = reader.readBit();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeLong(this.prefetchSize);
                writer.writeShort(this.prefetchCount);
                writer.writeBit(this.global);
            }
        }

        public static class QosOk
            extends Method
            implements com.rabbitmq.client.AMQP.File.QosOk
        {
            public static final int INDEX = 11;

            public QosOk() {}
            public int protocolClassId() { return 70; }
            public int protocolMethodId() { return 11; }
            public java.lang.String protocolMethodName() { return "file.qos-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
            }
        }

        public static class Consume
            extends Method
            implements com.rabbitmq.client.AMQP.File.Consume
        {
            public static final int INDEX = 20;

            public int ticket;
            public java.lang.String queue;
            public java.lang.String consumerTag;
            public boolean noLocal;
            public boolean noAck;
            public boolean exclusive;
            public boolean nowait;

            public int getTicket() { return ticket; }
            public java.lang.String getQueue() { return queue; }
            public java.lang.String getConsumerTag() { return consumerTag; }
            public boolean getNoLocal() { return noLocal; }
            public boolean getNoAck() { return noAck; }
            public boolean getExclusive() { return exclusive; }
            public boolean getNowait() { return nowait; }

            public Consume(
                int ticket,
                java.lang.String queue,
                java.lang.String consumerTag,
                boolean noLocal,
                boolean noAck,
                boolean exclusive,
                boolean nowait)
            {
                this.ticket = ticket;
                this.queue = queue;
                this.consumerTag = consumerTag;
                this.noLocal = noLocal;
                this.noAck = noAck;
                this.exclusive = exclusive;
                this.nowait = nowait;
            }

            public Consume() {}
            public int protocolClassId() { return 70; }
            public int protocolMethodId() { return 20; }
            public java.lang.String protocolMethodName() { return "file.consume";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("ticket=");
                acc.append(this.ticket);
                acc.append(",");
                acc.append("queue=");
                acc.append(this.queue);
                acc.append(",");
                acc.append("consumer-tag=");
                acc.append(this.consumerTag);
                acc.append(",");
                acc.append("no-local=");
                acc.append(this.noLocal);
                acc.append(",");
                acc.append("no-ack=");
                acc.append(this.noAck);
                acc.append(",");
                acc.append("exclusive=");
                acc.append(this.exclusive);
                acc.append(",");
                acc.append("nowait=");
                acc.append(this.nowait);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.ticket = reader.readShort();
                this.queue = reader.readShortstr();
                this.consumerTag = reader.readShortstr();
                this.noLocal = reader.readBit();
                this.noAck = reader.readBit();
                this.exclusive = reader.readBit();
                this.nowait = reader.readBit();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShort(this.ticket);
                writer.writeShortstr(this.queue);
                writer.writeShortstr(this.consumerTag);
                writer.writeBit(this.noLocal);
                writer.writeBit(this.noAck);
                writer.writeBit(this.exclusive);
                writer.writeBit(this.nowait);
            }
        }

        public static class ConsumeOk
            extends Method
            implements com.rabbitmq.client.AMQP.File.ConsumeOk
        {
            public static final int INDEX = 21;

            public java.lang.String consumerTag;

            public java.lang.String getConsumerTag() { return consumerTag; }

            public ConsumeOk(
                java.lang.String consumerTag)
            {
                this.consumerTag = consumerTag;
            }

            public ConsumeOk() {}
            public int protocolClassId() { return 70; }
            public int protocolMethodId() { return 21; }
            public java.lang.String protocolMethodName() { return "file.consume-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("consumer-tag=");
                acc.append(this.consumerTag);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.consumerTag = reader.readShortstr();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShortstr(this.consumerTag);
            }
        }

        public static class Cancel
            extends Method
            implements com.rabbitmq.client.AMQP.File.Cancel
        {
            public static final int INDEX = 30;

            public java.lang.String consumerTag;
            public boolean nowait;

            public java.lang.String getConsumerTag() { return consumerTag; }
            public boolean getNowait() { return nowait; }

            public Cancel(
                java.lang.String consumerTag,
                boolean nowait)
            {
                this.consumerTag = consumerTag;
                this.nowait = nowait;
            }

            public Cancel() {}
            public int protocolClassId() { return 70; }
            public int protocolMethodId() { return 30; }
            public java.lang.String protocolMethodName() { return "file.cancel";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("consumer-tag=");
                acc.append(this.consumerTag);
                acc.append(",");
                acc.append("nowait=");
                acc.append(this.nowait);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.consumerTag = reader.readShortstr();
                this.nowait = reader.readBit();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShortstr(this.consumerTag);
                writer.writeBit(this.nowait);
            }
        }

        public static class CancelOk
            extends Method
            implements com.rabbitmq.client.AMQP.File.CancelOk
        {
            public static final int INDEX = 31;

            public java.lang.String consumerTag;

            public java.lang.String getConsumerTag() { return consumerTag; }

            public CancelOk(
                java.lang.String consumerTag)
            {
                this.consumerTag = consumerTag;
            }

            public CancelOk() {}
            public int protocolClassId() { return 70; }
            public int protocolMethodId() { return 31; }
            public java.lang.String protocolMethodName() { return "file.cancel-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("consumer-tag=");
                acc.append(this.consumerTag);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.consumerTag = reader.readShortstr();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShortstr(this.consumerTag);
            }
        }

        public static class Open
            extends Method
            implements com.rabbitmq.client.AMQP.File.Open
        {
            public static final int INDEX = 40;

            public java.lang.String identifier;
            public long contentSize;

            public java.lang.String getIdentifier() { return identifier; }
            public long getContentSize() { return contentSize; }

            public Open(
                java.lang.String identifier,
                long contentSize)
            {
                this.identifier = identifier;
                this.contentSize = contentSize;
            }

            public Open() {}
            public int protocolClassId() { return 70; }
            public int protocolMethodId() { return 40; }
            public java.lang.String protocolMethodName() { return "file.open";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("identifier=");
                acc.append(this.identifier);
                acc.append(",");
                acc.append("content-size=");
                acc.append(this.contentSize);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.identifier = reader.readShortstr();
                this.contentSize = reader.readLonglong();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShortstr(this.identifier);
                writer.writeLonglong(this.contentSize);
            }
        }

        public static class OpenOk
            extends Method
            implements com.rabbitmq.client.AMQP.File.OpenOk
        {
            public static final int INDEX = 41;

            public long stagedSize;

            public long getStagedSize() { return stagedSize; }

            public OpenOk(
                long stagedSize)
            {
                this.stagedSize = stagedSize;
            }

            public OpenOk() {}
            public int protocolClassId() { return 70; }
            public int protocolMethodId() { return 41; }
            public java.lang.String protocolMethodName() { return "file.open-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("staged-size=");
                acc.append(this.stagedSize);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.stagedSize = reader.readLonglong();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeLonglong(this.stagedSize);
            }
        }

        public static class Stage
            extends Method
            implements com.rabbitmq.client.AMQP.File.Stage
        {
            public static final int INDEX = 50;

            public Stage() {}
            public int protocolClassId() { return 70; }
            public int protocolMethodId() { return 50; }
            public java.lang.String protocolMethodName() { return "file.stage";}

            public boolean hasContent() {
                return true;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
            }
        }

        public static class Publish
            extends Method
            implements com.rabbitmq.client.AMQP.File.Publish
        {
            public static final int INDEX = 60;

            public int ticket;
            public java.lang.String exchange;
            public java.lang.String routingKey;
            public boolean mandatory;
            public boolean immediate;
            public java.lang.String identifier;

            public int getTicket() { return ticket; }
            public java.lang.String getExchange() { return exchange; }
            public java.lang.String getRoutingKey() { return routingKey; }
            public boolean getMandatory() { return mandatory; }
            public boolean getImmediate() { return immediate; }
            public java.lang.String getIdentifier() { return identifier; }

            public Publish(
                int ticket,
                java.lang.String exchange,
                java.lang.String routingKey,
                boolean mandatory,
                boolean immediate,
                java.lang.String identifier)
            {
                this.ticket = ticket;
                this.exchange = exchange;
                this.routingKey = routingKey;
                this.mandatory = mandatory;
                this.immediate = immediate;
                this.identifier = identifier;
            }

            public Publish() {}
            public int protocolClassId() { return 70; }
            public int protocolMethodId() { return 60; }
            public java.lang.String protocolMethodName() { return "file.publish";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("ticket=");
                acc.append(this.ticket);
                acc.append(",");
                acc.append("exchange=");
                acc.append(this.exchange);
                acc.append(",");
                acc.append("routing-key=");
                acc.append(this.routingKey);
                acc.append(",");
                acc.append("mandatory=");
                acc.append(this.mandatory);
                acc.append(",");
                acc.append("immediate=");
                acc.append(this.immediate);
                acc.append(",");
                acc.append("identifier=");
                acc.append(this.identifier);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.ticket = reader.readShort();
                this.exchange = reader.readShortstr();
                this.routingKey = reader.readShortstr();
                this.mandatory = reader.readBit();
                this.immediate = reader.readBit();
                this.identifier = reader.readShortstr();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShort(this.ticket);
                writer.writeShortstr(this.exchange);
                writer.writeShortstr(this.routingKey);
                writer.writeBit(this.mandatory);
                writer.writeBit(this.immediate);
                writer.writeShortstr(this.identifier);
            }
        }

        public static class Return
            extends Method
            implements com.rabbitmq.client.AMQP.File.Return
        {
            public static final int INDEX = 70;

            public int replyCode;
            public java.lang.String replyText;
            public java.lang.String exchange;
            public java.lang.String routingKey;

            public int getReplyCode() { return replyCode; }
            public java.lang.String getReplyText() { return replyText; }
            public java.lang.String getExchange() { return exchange; }
            public java.lang.String getRoutingKey() { return routingKey; }

            public Return(
                int replyCode,
                java.lang.String replyText,
                java.lang.String exchange,
                java.lang.String routingKey)
            {
                this.replyCode = replyCode;
                this.replyText = replyText;
                this.exchange = exchange;
                this.routingKey = routingKey;
            }

            public Return() {}
            public int protocolClassId() { return 70; }
            public int protocolMethodId() { return 70; }
            public java.lang.String protocolMethodName() { return "file.return";}

            public boolean hasContent() {
                return true;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("reply-code=");
                acc.append(this.replyCode);
                acc.append(",");
                acc.append("reply-text=");
                acc.append(this.replyText);
                acc.append(",");
                acc.append("exchange=");
                acc.append(this.exchange);
                acc.append(",");
                acc.append("routing-key=");
                acc.append(this.routingKey);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.replyCode = reader.readShort();
                this.replyText = reader.readShortstr();
                this.exchange = reader.readShortstr();
                this.routingKey = reader.readShortstr();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShort(this.replyCode);
                writer.writeShortstr(this.replyText);
                writer.writeShortstr(this.exchange);
                writer.writeShortstr(this.routingKey);
            }
        }

        public static class Deliver
            extends Method
            implements com.rabbitmq.client.AMQP.File.Deliver
        {
            public static final int INDEX = 80;

            public java.lang.String consumerTag;
            public long deliveryTag;
            public boolean redelivered;
            public java.lang.String exchange;
            public java.lang.String routingKey;
            public java.lang.String identifier;

            public java.lang.String getConsumerTag() { return consumerTag; }
            public long getDeliveryTag() { return deliveryTag; }
            public boolean getRedelivered() { return redelivered; }
            public java.lang.String getExchange() { return exchange; }
            public java.lang.String getRoutingKey() { return routingKey; }
            public java.lang.String getIdentifier() { return identifier; }

            public Deliver(
                java.lang.String consumerTag,
                long deliveryTag,
                boolean redelivered,
                java.lang.String exchange,
                java.lang.String routingKey,
                java.lang.String identifier)
            {
                this.consumerTag = consumerTag;
                this.deliveryTag = deliveryTag;
                this.redelivered = redelivered;
                this.exchange = exchange;
                this.routingKey = routingKey;
                this.identifier = identifier;
            }

            public Deliver() {}
            public int protocolClassId() { return 70; }
            public int protocolMethodId() { return 80; }
            public java.lang.String protocolMethodName() { return "file.deliver";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("consumer-tag=");
                acc.append(this.consumerTag);
                acc.append(",");
                acc.append("delivery-tag=");
                acc.append(this.deliveryTag);
                acc.append(",");
                acc.append("redelivered=");
                acc.append(this.redelivered);
                acc.append(",");
                acc.append("exchange=");
                acc.append(this.exchange);
                acc.append(",");
                acc.append("routing-key=");
                acc.append(this.routingKey);
                acc.append(",");
                acc.append("identifier=");
                acc.append(this.identifier);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.consumerTag = reader.readShortstr();
                this.deliveryTag = reader.readLonglong();
                this.redelivered = reader.readBit();
                this.exchange = reader.readShortstr();
                this.routingKey = reader.readShortstr();
                this.identifier = reader.readShortstr();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShortstr(this.consumerTag);
                writer.writeLonglong(this.deliveryTag);
                writer.writeBit(this.redelivered);
                writer.writeShortstr(this.exchange);
                writer.writeShortstr(this.routingKey);
                writer.writeShortstr(this.identifier);
            }
        }

        public static class Ack
            extends Method
            implements com.rabbitmq.client.AMQP.File.Ack
        {
            public static final int INDEX = 90;

            public long deliveryTag;
            public boolean multiple;

            public long getDeliveryTag() { return deliveryTag; }
            public boolean getMultiple() { return multiple; }

            public Ack(
                long deliveryTag,
                boolean multiple)
            {
                this.deliveryTag = deliveryTag;
                this.multiple = multiple;
            }

            public Ack() {}
            public int protocolClassId() { return 70; }
            public int protocolMethodId() { return 90; }
            public java.lang.String protocolMethodName() { return "file.ack";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("delivery-tag=");
                acc.append(this.deliveryTag);
                acc.append(",");
                acc.append("multiple=");
                acc.append(this.multiple);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.deliveryTag = reader.readLonglong();
                this.multiple = reader.readBit();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeLonglong(this.deliveryTag);
                writer.writeBit(this.multiple);
            }
        }

        public static class Reject
            extends Method
            implements com.rabbitmq.client.AMQP.File.Reject
        {
            public static final int INDEX = 100;

            public long deliveryTag;
            public boolean requeue;

            public long getDeliveryTag() { return deliveryTag; }
            public boolean getRequeue() { return requeue; }

            public Reject(
                long deliveryTag,
                boolean requeue)
            {
                this.deliveryTag = deliveryTag;
                this.requeue = requeue;
            }

            public Reject() {}
            public int protocolClassId() { return 70; }
            public int protocolMethodId() { return 100; }
            public java.lang.String protocolMethodName() { return "file.reject";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("delivery-tag=");
                acc.append(this.deliveryTag);
                acc.append(",");
                acc.append("requeue=");
                acc.append(this.requeue);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.deliveryTag = reader.readLonglong();
                this.requeue = reader.readBit();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeLonglong(this.deliveryTag);
                writer.writeBit(this.requeue);
            }
        }
    }

    public static class Stream {
        public static final int INDEX = 80;

        public static class Qos
            extends Method
            implements com.rabbitmq.client.AMQP.Stream.Qos
        {
            public static final int INDEX = 10;

            public int prefetchSize;
            public int prefetchCount;
            public int consumeRate;
            public boolean global;

            public int getPrefetchSize() { return prefetchSize; }
            public int getPrefetchCount() { return prefetchCount; }
            public int getConsumeRate() { return consumeRate; }
            public boolean getGlobal() { return global; }

            public Qos(
                int prefetchSize,
                int prefetchCount,
                int consumeRate,
                boolean global)
            {
                this.prefetchSize = prefetchSize;
                this.prefetchCount = prefetchCount;
                this.consumeRate = consumeRate;
                this.global = global;
            }

            public Qos() {}
            public int protocolClassId() { return 80; }
            public int protocolMethodId() { return 10; }
            public java.lang.String protocolMethodName() { return "stream.qos";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("prefetch-size=");
                acc.append(this.prefetchSize);
                acc.append(",");
                acc.append("prefetch-count=");
                acc.append(this.prefetchCount);
                acc.append(",");
                acc.append("consume-rate=");
                acc.append(this.consumeRate);
                acc.append(",");
                acc.append("global=");
                acc.append(this.global);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.prefetchSize = reader.readLong();
                this.prefetchCount = reader.readShort();
                this.consumeRate = reader.readLong();
                this.global = reader.readBit();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeLong(this.prefetchSize);
                writer.writeShort(this.prefetchCount);
                writer.writeLong(this.consumeRate);
                writer.writeBit(this.global);
            }
        }

        public static class QosOk
            extends Method
            implements com.rabbitmq.client.AMQP.Stream.QosOk
        {
            public static final int INDEX = 11;

            public QosOk() {}
            public int protocolClassId() { return 80; }
            public int protocolMethodId() { return 11; }
            public java.lang.String protocolMethodName() { return "stream.qos-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
            }
        }

        public static class Consume
            extends Method
            implements com.rabbitmq.client.AMQP.Stream.Consume
        {
            public static final int INDEX = 20;

            public int ticket;
            public java.lang.String queue;
            public java.lang.String consumerTag;
            public boolean noLocal;
            public boolean exclusive;
            public boolean nowait;

            public int getTicket() { return ticket; }
            public java.lang.String getQueue() { return queue; }
            public java.lang.String getConsumerTag() { return consumerTag; }
            public boolean getNoLocal() { return noLocal; }
            public boolean getExclusive() { return exclusive; }
            public boolean getNowait() { return nowait; }

            public Consume(
                int ticket,
                java.lang.String queue,
                java.lang.String consumerTag,
                boolean noLocal,
                boolean exclusive,
                boolean nowait)
            {
                this.ticket = ticket;
                this.queue = queue;
                this.consumerTag = consumerTag;
                this.noLocal = noLocal;
                this.exclusive = exclusive;
                this.nowait = nowait;
            }

            public Consume() {}
            public int protocolClassId() { return 80; }
            public int protocolMethodId() { return 20; }
            public java.lang.String protocolMethodName() { return "stream.consume";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("ticket=");
                acc.append(this.ticket);
                acc.append(",");
                acc.append("queue=");
                acc.append(this.queue);
                acc.append(",");
                acc.append("consumer-tag=");
                acc.append(this.consumerTag);
                acc.append(",");
                acc.append("no-local=");
                acc.append(this.noLocal);
                acc.append(",");
                acc.append("exclusive=");
                acc.append(this.exclusive);
                acc.append(",");
                acc.append("nowait=");
                acc.append(this.nowait);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.ticket = reader.readShort();
                this.queue = reader.readShortstr();
                this.consumerTag = reader.readShortstr();
                this.noLocal = reader.readBit();
                this.exclusive = reader.readBit();
                this.nowait = reader.readBit();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShort(this.ticket);
                writer.writeShortstr(this.queue);
                writer.writeShortstr(this.consumerTag);
                writer.writeBit(this.noLocal);
                writer.writeBit(this.exclusive);
                writer.writeBit(this.nowait);
            }
        }

        public static class ConsumeOk
            extends Method
            implements com.rabbitmq.client.AMQP.Stream.ConsumeOk
        {
            public static final int INDEX = 21;

            public java.lang.String consumerTag;

            public java.lang.String getConsumerTag() { return consumerTag; }

            public ConsumeOk(
                java.lang.String consumerTag)
            {
                this.consumerTag = consumerTag;
            }

            public ConsumeOk() {}
            public int protocolClassId() { return 80; }
            public int protocolMethodId() { return 21; }
            public java.lang.String protocolMethodName() { return "stream.consume-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("consumer-tag=");
                acc.append(this.consumerTag);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.consumerTag = reader.readShortstr();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShortstr(this.consumerTag);
            }
        }

        public static class Cancel
            extends Method
            implements com.rabbitmq.client.AMQP.Stream.Cancel
        {
            public static final int INDEX = 30;

            public java.lang.String consumerTag;
            public boolean nowait;

            public java.lang.String getConsumerTag() { return consumerTag; }
            public boolean getNowait() { return nowait; }

            public Cancel(
                java.lang.String consumerTag,
                boolean nowait)
            {
                this.consumerTag = consumerTag;
                this.nowait = nowait;
            }

            public Cancel() {}
            public int protocolClassId() { return 80; }
            public int protocolMethodId() { return 30; }
            public java.lang.String protocolMethodName() { return "stream.cancel";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("consumer-tag=");
                acc.append(this.consumerTag);
                acc.append(",");
                acc.append("nowait=");
                acc.append(this.nowait);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.consumerTag = reader.readShortstr();
                this.nowait = reader.readBit();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShortstr(this.consumerTag);
                writer.writeBit(this.nowait);
            }
        }

        public static class CancelOk
            extends Method
            implements com.rabbitmq.client.AMQP.Stream.CancelOk
        {
            public static final int INDEX = 31;

            public java.lang.String consumerTag;

            public java.lang.String getConsumerTag() { return consumerTag; }

            public CancelOk(
                java.lang.String consumerTag)
            {
                this.consumerTag = consumerTag;
            }

            public CancelOk() {}
            public int protocolClassId() { return 80; }
            public int protocolMethodId() { return 31; }
            public java.lang.String protocolMethodName() { return "stream.cancel-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("consumer-tag=");
                acc.append(this.consumerTag);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.consumerTag = reader.readShortstr();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShortstr(this.consumerTag);
            }
        }

        public static class Publish
            extends Method
            implements com.rabbitmq.client.AMQP.Stream.Publish
        {
            public static final int INDEX = 40;

            public int ticket;
            public java.lang.String exchange;
            public java.lang.String routingKey;
            public boolean mandatory;
            public boolean immediate;

            public int getTicket() { return ticket; }
            public java.lang.String getExchange() { return exchange; }
            public java.lang.String getRoutingKey() { return routingKey; }
            public boolean getMandatory() { return mandatory; }
            public boolean getImmediate() { return immediate; }

            public Publish(
                int ticket,
                java.lang.String exchange,
                java.lang.String routingKey,
                boolean mandatory,
                boolean immediate)
            {
                this.ticket = ticket;
                this.exchange = exchange;
                this.routingKey = routingKey;
                this.mandatory = mandatory;
                this.immediate = immediate;
            }

            public Publish() {}
            public int protocolClassId() { return 80; }
            public int protocolMethodId() { return 40; }
            public java.lang.String protocolMethodName() { return "stream.publish";}

            public boolean hasContent() {
                return true;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("ticket=");
                acc.append(this.ticket);
                acc.append(",");
                acc.append("exchange=");
                acc.append(this.exchange);
                acc.append(",");
                acc.append("routing-key=");
                acc.append(this.routingKey);
                acc.append(",");
                acc.append("mandatory=");
                acc.append(this.mandatory);
                acc.append(",");
                acc.append("immediate=");
                acc.append(this.immediate);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.ticket = reader.readShort();
                this.exchange = reader.readShortstr();
                this.routingKey = reader.readShortstr();
                this.mandatory = reader.readBit();
                this.immediate = reader.readBit();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShort(this.ticket);
                writer.writeShortstr(this.exchange);
                writer.writeShortstr(this.routingKey);
                writer.writeBit(this.mandatory);
                writer.writeBit(this.immediate);
            }
        }

        public static class Return
            extends Method
            implements com.rabbitmq.client.AMQP.Stream.Return
        {
            public static final int INDEX = 50;

            public int replyCode;
            public java.lang.String replyText;
            public java.lang.String exchange;
            public java.lang.String routingKey;

            public int getReplyCode() { return replyCode; }
            public java.lang.String getReplyText() { return replyText; }
            public java.lang.String getExchange() { return exchange; }
            public java.lang.String getRoutingKey() { return routingKey; }

            public Return(
                int replyCode,
                java.lang.String replyText,
                java.lang.String exchange,
                java.lang.String routingKey)
            {
                this.replyCode = replyCode;
                this.replyText = replyText;
                this.exchange = exchange;
                this.routingKey = routingKey;
            }

            public Return() {}
            public int protocolClassId() { return 80; }
            public int protocolMethodId() { return 50; }
            public java.lang.String protocolMethodName() { return "stream.return";}

            public boolean hasContent() {
                return true;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("reply-code=");
                acc.append(this.replyCode);
                acc.append(",");
                acc.append("reply-text=");
                acc.append(this.replyText);
                acc.append(",");
                acc.append("exchange=");
                acc.append(this.exchange);
                acc.append(",");
                acc.append("routing-key=");
                acc.append(this.routingKey);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.replyCode = reader.readShort();
                this.replyText = reader.readShortstr();
                this.exchange = reader.readShortstr();
                this.routingKey = reader.readShortstr();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShort(this.replyCode);
                writer.writeShortstr(this.replyText);
                writer.writeShortstr(this.exchange);
                writer.writeShortstr(this.routingKey);
            }
        }

        public static class Deliver
            extends Method
            implements com.rabbitmq.client.AMQP.Stream.Deliver
        {
            public static final int INDEX = 60;

            public java.lang.String consumerTag;
            public long deliveryTag;
            public java.lang.String exchange;
            public java.lang.String queue;

            public java.lang.String getConsumerTag() { return consumerTag; }
            public long getDeliveryTag() { return deliveryTag; }
            public java.lang.String getExchange() { return exchange; }
            public java.lang.String getQueue() { return queue; }

            public Deliver(
                java.lang.String consumerTag,
                long deliveryTag,
                java.lang.String exchange,
                java.lang.String queue)
            {
                this.consumerTag = consumerTag;
                this.deliveryTag = deliveryTag;
                this.exchange = exchange;
                this.queue = queue;
            }

            public Deliver() {}
            public int protocolClassId() { return 80; }
            public int protocolMethodId() { return 60; }
            public java.lang.String protocolMethodName() { return "stream.deliver";}

            public boolean hasContent() {
                return true;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("consumer-tag=");
                acc.append(this.consumerTag);
                acc.append(",");
                acc.append("delivery-tag=");
                acc.append(this.deliveryTag);
                acc.append(",");
                acc.append("exchange=");
                acc.append(this.exchange);
                acc.append(",");
                acc.append("queue=");
                acc.append(this.queue);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.consumerTag = reader.readShortstr();
                this.deliveryTag = reader.readLonglong();
                this.exchange = reader.readShortstr();
                this.queue = reader.readShortstr();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShortstr(this.consumerTag);
                writer.writeLonglong(this.deliveryTag);
                writer.writeShortstr(this.exchange);
                writer.writeShortstr(this.queue);
            }
        }
    }

    public static class Tx {
        public static final int INDEX = 90;

        public static class Select
            extends Method
            implements com.rabbitmq.client.AMQP.Tx.Select
        {
            public static final int INDEX = 10;

            public Select() {}
            public int protocolClassId() { return 90; }
            public int protocolMethodId() { return 10; }
            public java.lang.String protocolMethodName() { return "tx.select";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
            }
        }

        public static class SelectOk
            extends Method
            implements com.rabbitmq.client.AMQP.Tx.SelectOk
        {
            public static final int INDEX = 11;

            public SelectOk() {}
            public int protocolClassId() { return 90; }
            public int protocolMethodId() { return 11; }
            public java.lang.String protocolMethodName() { return "tx.select-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
            }
        }

        public static class Commit
            extends Method
            implements com.rabbitmq.client.AMQP.Tx.Commit
        {
            public static final int INDEX = 20;

            public Commit() {}
            public int protocolClassId() { return 90; }
            public int protocolMethodId() { return 20; }
            public java.lang.String protocolMethodName() { return "tx.commit";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
            }
        }

        public static class CommitOk
            extends Method
            implements com.rabbitmq.client.AMQP.Tx.CommitOk
        {
            public static final int INDEX = 21;

            public CommitOk() {}
            public int protocolClassId() { return 90; }
            public int protocolMethodId() { return 21; }
            public java.lang.String protocolMethodName() { return "tx.commit-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
            }
        }

        public static class Rollback
            extends Method
            implements com.rabbitmq.client.AMQP.Tx.Rollback
        {
            public static final int INDEX = 30;

            public Rollback() {}
            public int protocolClassId() { return 90; }
            public int protocolMethodId() { return 30; }
            public java.lang.String protocolMethodName() { return "tx.rollback";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
            }
        }

        public static class RollbackOk
            extends Method
            implements com.rabbitmq.client.AMQP.Tx.RollbackOk
        {
            public static final int INDEX = 31;

            public RollbackOk() {}
            public int protocolClassId() { return 90; }
            public int protocolMethodId() { return 31; }
            public java.lang.String protocolMethodName() { return "tx.rollback-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
            }
        }
    }

    public static class Dtx {
        public static final int INDEX = 100;

        public static class Select
            extends Method
            implements com.rabbitmq.client.AMQP.Dtx.Select
        {
            public static final int INDEX = 10;

            public Select() {}
            public int protocolClassId() { return 100; }
            public int protocolMethodId() { return 10; }
            public java.lang.String protocolMethodName() { return "dtx.select";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
            }
        }

        public static class SelectOk
            extends Method
            implements com.rabbitmq.client.AMQP.Dtx.SelectOk
        {
            public static final int INDEX = 11;

            public SelectOk() {}
            public int protocolClassId() { return 100; }
            public int protocolMethodId() { return 11; }
            public java.lang.String protocolMethodName() { return "dtx.select-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
            }
        }

        public static class Start
            extends Method
            implements com.rabbitmq.client.AMQP.Dtx.Start
        {
            public static final int INDEX = 20;

            public java.lang.String dtxIdentifier;

            public java.lang.String getDtxIdentifier() { return dtxIdentifier; }

            public Start(
                java.lang.String dtxIdentifier)
            {
                this.dtxIdentifier = dtxIdentifier;
            }

            public Start() {}
            public int protocolClassId() { return 100; }
            public int protocolMethodId() { return 20; }
            public java.lang.String protocolMethodName() { return "dtx.start";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("dtx-identifier=");
                acc.append(this.dtxIdentifier);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.dtxIdentifier = reader.readShortstr();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShortstr(this.dtxIdentifier);
            }
        }

        public static class StartOk
            extends Method
            implements com.rabbitmq.client.AMQP.Dtx.StartOk
        {
            public static final int INDEX = 21;

            public StartOk() {}
            public int protocolClassId() { return 100; }
            public int protocolMethodId() { return 21; }
            public java.lang.String protocolMethodName() { return "dtx.start-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
            }
        }
    }

    public static class Tunnel {
        public static final int INDEX = 110;

        public static class Request
            extends Method
            implements com.rabbitmq.client.AMQP.Tunnel.Request
        {
            public static final int INDEX = 10;

            public Map<java.lang.String,Object> metaData;

            public Map<java.lang.String,Object> getMetaData() { return metaData; }

            public Request(
                Map<java.lang.String,Object> metaData)
            {
                this.metaData = metaData;
            }

            public Request() {}
            public int protocolClassId() { return 110; }
            public int protocolMethodId() { return 10; }
            public java.lang.String protocolMethodName() { return "tunnel.request";}

            public boolean hasContent() {
                return true;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("meta-data=");
                acc.append(this.metaData);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.metaData = reader.readTable();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeTable(this.metaData);
            }
        }
    }

    public static class Test {
        public static final int INDEX = 120;

        public static class Integer
            extends Method
            implements com.rabbitmq.client.AMQP.Test.Integer
        {
            public static final int INDEX = 10;

            public int integer1;
            public int integer2;
            public int integer3;
            public long integer4;
            public int operation;

            public int getInteger1() { return integer1; }
            public int getInteger2() { return integer2; }
            public int getInteger3() { return integer3; }
            public long getInteger4() { return integer4; }
            public int getOperation() { return operation; }

            public Integer(
                int integer1,
                int integer2,
                int integer3,
                long integer4,
                int operation)
            {
                this.integer1 = integer1;
                this.integer2 = integer2;
                this.integer3 = integer3;
                this.integer4 = integer4;
                this.operation = operation;
            }

            public Integer() {}
            public int protocolClassId() { return 120; }
            public int protocolMethodId() { return 10; }
            public java.lang.String protocolMethodName() { return "test.integer";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("integer-1=");
                acc.append(this.integer1);
                acc.append(",");
                acc.append("integer-2=");
                acc.append(this.integer2);
                acc.append(",");
                acc.append("integer-3=");
                acc.append(this.integer3);
                acc.append(",");
                acc.append("integer-4=");
                acc.append(this.integer4);
                acc.append(",");
                acc.append("operation=");
                acc.append(this.operation);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.integer1 = reader.readOctet();
                this.integer2 = reader.readShort();
                this.integer3 = reader.readLong();
                this.integer4 = reader.readLonglong();
                this.operation = reader.readOctet();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeOctet(this.integer1);
                writer.writeShort(this.integer2);
                writer.writeLong(this.integer3);
                writer.writeLonglong(this.integer4);
                writer.writeOctet(this.operation);
            }
        }

        public static class IntegerOk
            extends Method
            implements com.rabbitmq.client.AMQP.Test.IntegerOk
        {
            public static final int INDEX = 11;

            public long result;

            public long getResult() { return result; }

            public IntegerOk(
                long result)
            {
                this.result = result;
            }

            public IntegerOk() {}
            public int protocolClassId() { return 120; }
            public int protocolMethodId() { return 11; }
            public java.lang.String protocolMethodName() { return "test.integer-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("result=");
                acc.append(this.result);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.result = reader.readLonglong();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeLonglong(this.result);
            }
        }

        public static class String
            extends Method
            implements com.rabbitmq.client.AMQP.Test.String
        {
            public static final int INDEX = 20;

            public java.lang.String string1;
            public LongString string2;
            public int operation;

            public java.lang.String getString1() { return string1; }
            public LongString getString2() { return string2; }
            public int getOperation() { return operation; }

            public String(
                java.lang.String string1,
                LongString string2,
                int operation)
            {
                this.string1 = string1;
                this.string2 = string2;
                this.operation = operation;
            }

            public String() {}
            public int protocolClassId() { return 120; }
            public int protocolMethodId() { return 20; }
            public java.lang.String protocolMethodName() { return "test.string";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("string-1=");
                acc.append(this.string1);
                acc.append(",");
                acc.append("string-2=");
                acc.append(this.string2);
                acc.append(",");
                acc.append("operation=");
                acc.append(this.operation);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.string1 = reader.readShortstr();
                this.string2 = reader.readLongstr();
                this.operation = reader.readOctet();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeShortstr(this.string1);
                writer.writeLongstr(this.string2);
                writer.writeOctet(this.operation);
            }
        }

        public static class StringOk
            extends Method
            implements com.rabbitmq.client.AMQP.Test.StringOk
        {
            public static final int INDEX = 21;

            public LongString result;

            public LongString getResult() { return result; }

            public StringOk(
                LongString result)
            {
                this.result = result;
            }

            public StringOk() {}
            public int protocolClassId() { return 120; }
            public int protocolMethodId() { return 21; }
            public java.lang.String protocolMethodName() { return "test.string-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("result=");
                acc.append(this.result);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.result = reader.readLongstr();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeLongstr(this.result);
            }
        }

        public static class Table
            extends Method
            implements com.rabbitmq.client.AMQP.Test.Table
        {
            public static final int INDEX = 30;

            public Map<java.lang.String,Object> table;
            public int integerOp;
            public int stringOp;

            public Map<java.lang.String,Object> getTable() { return table; }
            public int getIntegerOp() { return integerOp; }
            public int getStringOp() { return stringOp; }

            public Table(
                Map<java.lang.String,Object> table,
                int integerOp,
                int stringOp)
            {
                this.table = table;
                this.integerOp = integerOp;
                this.stringOp = stringOp;
            }

            public Table() {}
            public int protocolClassId() { return 120; }
            public int protocolMethodId() { return 30; }
            public java.lang.String protocolMethodName() { return "test.table";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("table=");
                acc.append(this.table);
                acc.append(",");
                acc.append("integer-op=");
                acc.append(this.integerOp);
                acc.append(",");
                acc.append("string-op=");
                acc.append(this.stringOp);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.table = reader.readTable();
                this.integerOp = reader.readOctet();
                this.stringOp = reader.readOctet();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeTable(this.table);
                writer.writeOctet(this.integerOp);
                writer.writeOctet(this.stringOp);
            }
        }

        public static class TableOk
            extends Method
            implements com.rabbitmq.client.AMQP.Test.TableOk
        {
            public static final int INDEX = 31;

            public long integerResult;
            public LongString stringResult;

            public long getIntegerResult() { return integerResult; }
            public LongString getStringResult() { return stringResult; }

            public TableOk(
                long integerResult,
                LongString stringResult)
            {
                this.integerResult = integerResult;
                this.stringResult = stringResult;
            }

            public TableOk() {}
            public int protocolClassId() { return 120; }
            public int protocolMethodId() { return 31; }
            public java.lang.String protocolMethodName() { return "test.table-ok";}

            public boolean hasContent() {
                return false;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("integer-result=");
                acc.append(this.integerResult);
                acc.append(",");
                acc.append("string-result=");
                acc.append(this.stringResult);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.integerResult = reader.readLonglong();
                this.stringResult = reader.readLongstr();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeLonglong(this.integerResult);
                writer.writeLongstr(this.stringResult);
            }
        }

        public static class Content
            extends Method
            implements com.rabbitmq.client.AMQP.Test.Content
        {
            public static final int INDEX = 40;

            public Content() {}
            public int protocolClassId() { return 120; }
            public int protocolMethodId() { return 40; }
            public java.lang.String protocolMethodName() { return "test.content";}

            public boolean hasContent() {
                return true;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
            }
        }

        public static class ContentOk
            extends Method
            implements com.rabbitmq.client.AMQP.Test.ContentOk
        {
            public static final int INDEX = 41;

            public int contentChecksum;

            public int getContentChecksum() { return contentChecksum; }

            public ContentOk(
                int contentChecksum)
            {
                this.contentChecksum = contentChecksum;
            }

            public ContentOk() {}
            public int protocolClassId() { return 120; }
            public int protocolMethodId() { return 41; }
            public java.lang.String protocolMethodName() { return "test.content-ok";}

            public boolean hasContent() {
                return true;
            }

            public Object visit(MethodVisitor visitor) throws IOException {
                return visitor.visit(this);
            }

            public void appendArgumentDebugStringTo(StringBuffer acc) {
                acc.append("(");
                acc.append("content-checksum=");
                acc.append(this.contentChecksum);
                acc.append(")");
            }

            public void readArgumentsFrom(MethodArgumentReader reader)
                throws IOException
            {
                this.contentChecksum = reader.readLong();
            }

            public void writeArgumentsTo(MethodArgumentWriter writer)
                throws IOException
            {
                writer.writeLong(this.contentChecksum);
            }
        }
    }

    public interface MethodVisitor {
        Object visit(Connection.Start x) throws IOException;
        Object visit(Connection.StartOk x) throws IOException;
        Object visit(Connection.Secure x) throws IOException;
        Object visit(Connection.SecureOk x) throws IOException;
        Object visit(Connection.Tune x) throws IOException;
        Object visit(Connection.TuneOk x) throws IOException;
        Object visit(Connection.Open x) throws IOException;
        Object visit(Connection.OpenOk x) throws IOException;
        Object visit(Connection.Redirect x) throws IOException;
        Object visit(Connection.Close x) throws IOException;
        Object visit(Connection.CloseOk x) throws IOException;
        Object visit(Channel.Open x) throws IOException;
        Object visit(Channel.OpenOk x) throws IOException;
        Object visit(Channel.Flow x) throws IOException;
        Object visit(Channel.FlowOk x) throws IOException;
        Object visit(Channel.Alert x) throws IOException;
        Object visit(Channel.Close x) throws IOException;
        Object visit(Channel.CloseOk x) throws IOException;
        Object visit(Access.Request x) throws IOException;
        Object visit(Access.RequestOk x) throws IOException;
        Object visit(Exchange.Declare x) throws IOException;
        Object visit(Exchange.DeclareOk x) throws IOException;
        Object visit(Exchange.Delete x) throws IOException;
        Object visit(Exchange.DeleteOk x) throws IOException;
        Object visit(Queue.Declare x) throws IOException;
        Object visit(Queue.DeclareOk x) throws IOException;
        Object visit(Queue.Bind x) throws IOException;
        Object visit(Queue.BindOk x) throws IOException;
        Object visit(Queue.Purge x) throws IOException;
        Object visit(Queue.PurgeOk x) throws IOException;
        Object visit(Queue.Delete x) throws IOException;
        Object visit(Queue.DeleteOk x) throws IOException;
        Object visit(Queue.Unbind x) throws IOException;
        Object visit(Queue.UnbindOk x) throws IOException;
        Object visit(Basic.Qos x) throws IOException;
        Object visit(Basic.QosOk x) throws IOException;
        Object visit(Basic.Consume x) throws IOException;
        Object visit(Basic.ConsumeOk x) throws IOException;
        Object visit(Basic.Cancel x) throws IOException;
        Object visit(Basic.CancelOk x) throws IOException;
        Object visit(Basic.Publish x) throws IOException;
        Object visit(Basic.Return x) throws IOException;
        Object visit(Basic.Deliver x) throws IOException;
        Object visit(Basic.Get x) throws IOException;
        Object visit(Basic.GetOk x) throws IOException;
        Object visit(Basic.GetEmpty x) throws IOException;
        Object visit(Basic.Ack x) throws IOException;
        Object visit(Basic.Reject x) throws IOException;
        Object visit(Basic.Recover x) throws IOException;
        Object visit(File.Qos x) throws IOException;
        Object visit(File.QosOk x) throws IOException;
        Object visit(File.Consume x) throws IOException;
        Object visit(File.ConsumeOk x) throws IOException;
        Object visit(File.Cancel x) throws IOException;
        Object visit(File.CancelOk x) throws IOException;
        Object visit(File.Open x) throws IOException;
        Object visit(File.OpenOk x) throws IOException;
        Object visit(File.Stage x) throws IOException;
        Object visit(File.Publish x) throws IOException;
        Object visit(File.Return x) throws IOException;
        Object visit(File.Deliver x) throws IOException;
        Object visit(File.Ack x) throws IOException;
        Object visit(File.Reject x) throws IOException;
        Object visit(Stream.Qos x) throws IOException;
        Object visit(Stream.QosOk x) throws IOException;
        Object visit(Stream.Consume x) throws IOException;
        Object visit(Stream.ConsumeOk x) throws IOException;
        Object visit(Stream.Cancel x) throws IOException;
        Object visit(Stream.CancelOk x) throws IOException;
        Object visit(Stream.Publish x) throws IOException;
        Object visit(Stream.Return x) throws IOException;
        Object visit(Stream.Deliver x) throws IOException;
        Object visit(Tx.Select x) throws IOException;
        Object visit(Tx.SelectOk x) throws IOException;
        Object visit(Tx.Commit x) throws IOException;
        Object visit(Tx.CommitOk x) throws IOException;
        Object visit(Tx.Rollback x) throws IOException;
        Object visit(Tx.RollbackOk x) throws IOException;
        Object visit(Dtx.Select x) throws IOException;
        Object visit(Dtx.SelectOk x) throws IOException;
        Object visit(Dtx.Start x) throws IOException;
        Object visit(Dtx.StartOk x) throws IOException;
        Object visit(Tunnel.Request x) throws IOException;
        Object visit(Test.Integer x) throws IOException;
        Object visit(Test.IntegerOk x) throws IOException;
        Object visit(Test.String x) throws IOException;
        Object visit(Test.StringOk x) throws IOException;
        Object visit(Test.Table x) throws IOException;
        Object visit(Test.TableOk x) throws IOException;
        Object visit(Test.Content x) throws IOException;
        Object visit(Test.ContentOk x) throws IOException;
    }

    public static class DefaultMethodVisitor implements MethodVisitor {
        public Object visit(Connection.Start x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Connection.StartOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Connection.Secure x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Connection.SecureOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Connection.Tune x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Connection.TuneOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Connection.Open x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Connection.OpenOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Connection.Redirect x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Connection.Close x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Connection.CloseOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Channel.Open x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Channel.OpenOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Channel.Flow x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Channel.FlowOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Channel.Alert x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Channel.Close x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Channel.CloseOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Access.Request x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Access.RequestOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Exchange.Declare x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Exchange.DeclareOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Exchange.Delete x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Exchange.DeleteOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Queue.Declare x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Queue.DeclareOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Queue.Bind x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Queue.BindOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Queue.Purge x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Queue.PurgeOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Queue.Delete x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Queue.DeleteOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Queue.Unbind x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Queue.UnbindOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Basic.Qos x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Basic.QosOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Basic.Consume x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Basic.ConsumeOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Basic.Cancel x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Basic.CancelOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Basic.Publish x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Basic.Return x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Basic.Deliver x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Basic.Get x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Basic.GetOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Basic.GetEmpty x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Basic.Ack x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Basic.Reject x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Basic.Recover x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(File.Qos x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(File.QosOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(File.Consume x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(File.ConsumeOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(File.Cancel x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(File.CancelOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(File.Open x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(File.OpenOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(File.Stage x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(File.Publish x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(File.Return x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(File.Deliver x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(File.Ack x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(File.Reject x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Stream.Qos x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Stream.QosOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Stream.Consume x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Stream.ConsumeOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Stream.Cancel x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Stream.CancelOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Stream.Publish x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Stream.Return x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Stream.Deliver x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Tx.Select x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Tx.SelectOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Tx.Commit x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Tx.CommitOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Tx.Rollback x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Tx.RollbackOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Dtx.Select x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Dtx.SelectOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Dtx.Start x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Dtx.StartOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Tunnel.Request x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Test.Integer x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Test.IntegerOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Test.String x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Test.StringOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Test.Table x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Test.TableOk x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Test.Content x) throws IOException { throw new UnexpectedMethodError(x); } 
        public Object visit(Test.ContentOk x) throws IOException { throw new UnexpectedMethodError(x); } 
    }

    public static Method readMethodFrom(DataInputStream in) throws IOException { 
        int classId = in.readShort();
        int methodId = in.readShort();
        switch (classId) {
            case 10:
                switch (methodId) {
                    case 10: {
                        Connection.Start result = new Connection.Start();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 11: {
                        Connection.StartOk result = new Connection.StartOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 20: {
                        Connection.Secure result = new Connection.Secure();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 21: {
                        Connection.SecureOk result = new Connection.SecureOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 30: {
                        Connection.Tune result = new Connection.Tune();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 31: {
                        Connection.TuneOk result = new Connection.TuneOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 40: {
                        Connection.Open result = new Connection.Open();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 41: {
                        Connection.OpenOk result = new Connection.OpenOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 50: {
                        Connection.Redirect result = new Connection.Redirect();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 60: {
                        Connection.Close result = new Connection.Close();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 61: {
                        Connection.CloseOk result = new Connection.CloseOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    default: break;
                }
            case 20:
                switch (methodId) {
                    case 10: {
                        Channel.Open result = new Channel.Open();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 11: {
                        Channel.OpenOk result = new Channel.OpenOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 20: {
                        Channel.Flow result = new Channel.Flow();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 21: {
                        Channel.FlowOk result = new Channel.FlowOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 30: {
                        Channel.Alert result = new Channel.Alert();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 40: {
                        Channel.Close result = new Channel.Close();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 41: {
                        Channel.CloseOk result = new Channel.CloseOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    default: break;
                }
            case 30:
                switch (methodId) {
                    case 10: {
                        Access.Request result = new Access.Request();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 11: {
                        Access.RequestOk result = new Access.RequestOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    default: break;
                }
            case 40:
                switch (methodId) {
                    case 10: {
                        Exchange.Declare result = new Exchange.Declare();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 11: {
                        Exchange.DeclareOk result = new Exchange.DeclareOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 20: {
                        Exchange.Delete result = new Exchange.Delete();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 21: {
                        Exchange.DeleteOk result = new Exchange.DeleteOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    default: break;
                }
            case 50:
                switch (methodId) {
                    case 10: {
                        Queue.Declare result = new Queue.Declare();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 11: {
                        Queue.DeclareOk result = new Queue.DeclareOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 20: {
                        Queue.Bind result = new Queue.Bind();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 21: {
                        Queue.BindOk result = new Queue.BindOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 30: {
                        Queue.Purge result = new Queue.Purge();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 31: {
                        Queue.PurgeOk result = new Queue.PurgeOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 40: {
                        Queue.Delete result = new Queue.Delete();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 41: {
                        Queue.DeleteOk result = new Queue.DeleteOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 50: {
                        Queue.Unbind result = new Queue.Unbind();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 51: {
                        Queue.UnbindOk result = new Queue.UnbindOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    default: break;
                }
            case 60:
                switch (methodId) {
                    case 10: {
                        Basic.Qos result = new Basic.Qos();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 11: {
                        Basic.QosOk result = new Basic.QosOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 20: {
                        Basic.Consume result = new Basic.Consume();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 21: {
                        Basic.ConsumeOk result = new Basic.ConsumeOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 30: {
                        Basic.Cancel result = new Basic.Cancel();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 31: {
                        Basic.CancelOk result = new Basic.CancelOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 40: {
                        Basic.Publish result = new Basic.Publish();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 50: {
                        Basic.Return result = new Basic.Return();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 60: {
                        Basic.Deliver result = new Basic.Deliver();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 70: {
                        Basic.Get result = new Basic.Get();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 71: {
                        Basic.GetOk result = new Basic.GetOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 72: {
                        Basic.GetEmpty result = new Basic.GetEmpty();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 80: {
                        Basic.Ack result = new Basic.Ack();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 90: {
                        Basic.Reject result = new Basic.Reject();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 100: {
                        Basic.Recover result = new Basic.Recover();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    default: break;
                }
            case 70:
                switch (methodId) {
                    case 10: {
                        File.Qos result = new File.Qos();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 11: {
                        File.QosOk result = new File.QosOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 20: {
                        File.Consume result = new File.Consume();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 21: {
                        File.ConsumeOk result = new File.ConsumeOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 30: {
                        File.Cancel result = new File.Cancel();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 31: {
                        File.CancelOk result = new File.CancelOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 40: {
                        File.Open result = new File.Open();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 41: {
                        File.OpenOk result = new File.OpenOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 50: {
                        File.Stage result = new File.Stage();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 60: {
                        File.Publish result = new File.Publish();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 70: {
                        File.Return result = new File.Return();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 80: {
                        File.Deliver result = new File.Deliver();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 90: {
                        File.Ack result = new File.Ack();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 100: {
                        File.Reject result = new File.Reject();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    default: break;
                }
            case 80:
                switch (methodId) {
                    case 10: {
                        Stream.Qos result = new Stream.Qos();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 11: {
                        Stream.QosOk result = new Stream.QosOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 20: {
                        Stream.Consume result = new Stream.Consume();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 21: {
                        Stream.ConsumeOk result = new Stream.ConsumeOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 30: {
                        Stream.Cancel result = new Stream.Cancel();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 31: {
                        Stream.CancelOk result = new Stream.CancelOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 40: {
                        Stream.Publish result = new Stream.Publish();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 50: {
                        Stream.Return result = new Stream.Return();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 60: {
                        Stream.Deliver result = new Stream.Deliver();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    default: break;
                }
            case 90:
                switch (methodId) {
                    case 10: {
                        Tx.Select result = new Tx.Select();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 11: {
                        Tx.SelectOk result = new Tx.SelectOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 20: {
                        Tx.Commit result = new Tx.Commit();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 21: {
                        Tx.CommitOk result = new Tx.CommitOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 30: {
                        Tx.Rollback result = new Tx.Rollback();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 31: {
                        Tx.RollbackOk result = new Tx.RollbackOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    default: break;
                }
            case 100:
                switch (methodId) {
                    case 10: {
                        Dtx.Select result = new Dtx.Select();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 11: {
                        Dtx.SelectOk result = new Dtx.SelectOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 20: {
                        Dtx.Start result = new Dtx.Start();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 21: {
                        Dtx.StartOk result = new Dtx.StartOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    default: break;
                }
            case 110:
                switch (methodId) {
                    case 10: {
                        Tunnel.Request result = new Tunnel.Request();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    default: break;
                }
            case 120:
                switch (methodId) {
                    case 10: {
                        Test.Integer result = new Test.Integer();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 11: {
                        Test.IntegerOk result = new Test.IntegerOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 20: {
                        Test.String result = new Test.String();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 21: {
                        Test.StringOk result = new Test.StringOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 30: {
                        Test.Table result = new Test.Table();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 31: {
                        Test.TableOk result = new Test.TableOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 40: {
                        Test.Content result = new Test.Content();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    case 41: {
                        Test.ContentOk result = new Test.ContentOk();
                        result.readArgumentsFrom(new MethodArgumentReader(in));
                        return result;
                    }
                    default: break;
                }
        }

        throw new UnknownClassOrMethodId(classId, methodId);
    }

    public static AMQContentHeader readContentHeaderFrom(DataInputStream in)
        throws IOException
    {
        int classId = in.readShort();

        switch (classId) {
            case 60: return new BasicProperties();
            case 70: return new FileProperties();
            case 80: return new StreamProperties();
            case 110: return new TunnelProperties();
            default: break;
        }

        throw new UnknownClassOrMethodId(classId, -1);
    }
}
