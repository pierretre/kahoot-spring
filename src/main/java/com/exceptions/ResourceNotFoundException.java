package com.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(Class clazz, Long id) {
        super("Impossible de trouver la ressource de type "+clazz.getName()+" pour l'ID " + id);
    }
}
