package com.fabiansebastianj1.validations;

import java.util.Optional;
import java.util.Scanner;
import java.util.function.Supplier;

import com.google.protobuf.Option;

public class ValidationExist {
    
    public static <T> T transformAndValidateObj(Supplier<Optional<T>> supplier){
        T objetoSeleccionado = null;
        while (true) {
            Optional<T> objetoOpcional = supplier.get();
            if (objetoOpcional.isPresent()) {
                System.out.println();
                return objetoSeleccionado = objetoOpcional.get();
            } else {
                System.out.println("El id no existe");
            }
        }
    }
}