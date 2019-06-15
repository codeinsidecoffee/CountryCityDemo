
package com.mrlonewolfer.countrycitydemo;


import java.util.List;

import androidx.annotation.NonNull;

public class CountryRoot {


        String name,code;

    public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

    @Override
    public String toString() {
        return name;
    }
}
