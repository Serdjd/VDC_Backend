package com.treintaytres.vdc_backend;


import com.treintaytres.vdc_backend.model.Event;
import org.hibernate.CallbackException;
import org.hibernate.Interceptor;
import org.hibernate.type.Type;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class EventChangeInterceptor implements Interceptor {
    @Override
    public boolean onFlushDirty(Object entity, Object id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) throws CallbackException {

        if (entity instanceof Event) {
            Set<String> changedFields = new HashSet<>();

            for (int i = 0; i < propertyNames.length; i++) {
                if(!Objects.equals(currentState[i],previousState[i])){
                    changedFields.add(propertyNames[i]);
                }
            }
            if (changedFields.contains("type") || changedFields.contains("date") || changedFields.contains("location")) {
                //Dettales del evento acutalizados
            }
            if (changedFields.contains("comments")) {
                //SE han anadido comentarios al evento
            }
            if (changedFields.contains("userEvents")) {
                //Notify new users y los que ya no esten presentes
            }

        }

        return false;
    }
}
