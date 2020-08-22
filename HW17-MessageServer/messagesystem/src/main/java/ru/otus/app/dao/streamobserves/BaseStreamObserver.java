package ru.otus.app.dao.streamobserves;

import io.grpc.stub.StreamObserver;
import ru.otus.protobuf.generated.UserMessage;

import java.util.regex.Pattern;

public class BaseStreamObserver {

    public final Pattern pattern = Pattern.compile("@[0-9A-Fa-f]+$");
    public String key = null;

    public void DefineKey(StreamObserver<?> response){
        if (key == null) {
            var s = response.toString();
            var b = pattern.matcher(s);
            if (b.find()) {
                key = s.substring(b.start());
            }
        }
    }

    Boolean UserMessageNotEmpty(UserMessage value) {
        Boolean b = true;
        for (var field : value.getDescriptorForType().getFields()) {
            b &= value.hasField(field);
        }
        return b;
    }

}
