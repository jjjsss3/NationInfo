package com.example.exnetwork;

import java.io.Serializable;

public class Country  {
    public String countryName, countryCode, countryPopulation,areaInSqKm;
    public Country (String name, String code, String pop) {
        countryName = name;
        countryCode = code;
        countryPopulation = pop;
    }

    public Country(String countryName, String countryCode, String countryPopulation, String areaInSqKm) {
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.countryPopulation = countryPopulation;
        this.areaInSqKm = areaInSqKm;
    }

    public String toJSON() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"geonames\":[{\"population\":\"");
        sb.append(countryPopulation);
        sb.append("\",\"countryCode\":\"");
        sb.append(countryCode);
        sb.append("\",\"countryName\":\"");
        sb.append(countryName);
        sb.append("\",\"area\":\"");
        sb.append(areaInSqKm);
        sb.append("\"}]}");
        return sb.toString();
    }

    public String get_URL_FLAG_GIF() {
        return "https://img.geonames.org/flags/x/"+countryCode.toLowerCase()+".gif";
    }
    public String get_UR_MAP_IMAGE(){
        return "https://www.geonames.org/img/country/400/"+countryCode+".png";
    }
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryPopulation() {
        return countryPopulation;
    }

    public void setCountryPopulation(String countryPopulation) {
        this.countryPopulation = countryPopulation;
    }

    public String getAreaInSqKm() {
        return areaInSqKm;
    }

    public void setAreaInSqKm(String areaInSqKm) {
        this.areaInSqKm = areaInSqKm;
    }
}
