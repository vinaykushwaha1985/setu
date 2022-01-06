package com.gov.nha.bis.server.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class EmptyListFilter {
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof List)) {return false;}
        @SuppressWarnings("unchecked")
		Optional<Object> result = ((List)obj).stream().filter(
                eachObj -> Arrays.asList(eachObj.getClass().getDeclaredFields()).stream().filter(eachField -> {
                    try {
                        eachField.setAccessible(true);
                        if ( eachField.get(eachObj)  != null && !eachField.get(eachObj).toString().isEmpty()) {
                            return true;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return false;
                }).count() > 0).findAny();
       return  !result.isPresent();
    }
}
