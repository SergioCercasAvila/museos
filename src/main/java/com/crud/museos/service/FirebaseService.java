package com.crud.museos.service;

import com.crud.museos.model.Museo;
import com.google.firebase.database.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CountDownLatch;

@Service
public class FirebaseService {

    private final DatabaseReference ref;

    public FirebaseService() {
        this.ref = FirebaseDatabase.getInstance().getReference("museos");
    }

    // Guardar o actualizar museo
    public String guardarMuseo(Museo museo) {
        if (museo.getId() == null || museo.getId().isEmpty()) {
            // genera id aleatorio
            String id = ref.push().getKey();
            museo.setId(id);
        }
        ref.child(museo.getId()).setValueAsync(museo);
        return museo.getId();
    }

    // Actualizar museo existente
    public boolean actualizarMuseo(String id, Museo museoActualizado) {
        final boolean[] exito = {false};
        CountDownLatch latch = new CountDownLatch(1);

        ref.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    museoActualizado.setId(id); // asegura que el ID se mantenga
                    ref.child(id).setValueAsync(museoActualizado);
                    exito[0] = true;
                }
                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return exito[0];
    }

    // Obtener todos los museos
    public List<Museo> obtenerMuseos() {
        List<Museo> museos = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(1);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    Museo m = child.getValue(Museo.class);
                    museos.add(m);
                }
                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                latch.countDown();
            }
        });

        try {
            latch.await(); // espera que termine la lectura
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return museos;
    }

    // Obtener un museo por id
    public Museo obtenerMuseoPorId(String id) {
        final Museo[] museo = {null};
        CountDownLatch latch = new CountDownLatch(1);

        ref.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                museo[0] = snapshot.getValue(Museo.class);
                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return museo[0];
    }

    // Eliminar museo
    public void eliminarMuseo(String id) {
        ref.child(id).removeValueAsync();
    }
}
