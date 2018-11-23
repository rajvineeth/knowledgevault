/**
 * @author: jshyperx
 */

package com.stackroute.knowledgevault.paragraphprocessor.utilities;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FillUpData {

    private static final String JSONTEMPLATE = "{" +
            " \"@context\": \"http://schema.org\"," +
            " \"@type\": \"MedicalCondition\"," +
            " \"alternateName\": \"null\"," +
            " \"associatedAnatomy\": {" +
            "   \"@type\": \"AnatomicalStructure\"," +
            "   \"name\": \"null\"" +
            " }," +
            " \"cause\": [" +
            "   {" +
            "     \"@type\": \"MedicalCause\"," +
            "     \"name\": \"null\"" +
            "   }," +
            "   {" +
            "     \"@type\": \"MedicalCause\"," +
            "     \"name\": \"null\"" +
            "   }" +
            " ]," +
            " \"code\": {" +
            "   \"@type\": \"MedicalCode\"," +
            "   \"code\": \"413\"," +
            "   \"codingSystem\": \"ICD-9\"" +
            " }," +
            " \"differentialDiagnosis\": {" +
            "   \"distinguishingSign\": [" +
            "     {" +
            "       \"@type\": \"MedicalSymptom\"," +
            "       \"name\": \"null\"" +
            "     }," +
            "     {" +
            "       \"@type\": \"MedicalSymptom\"," +
            "       \"name\": \"null\"" +
            "     }," +
            "     {" +
            "       \"@type\": \"MedicalSymptom\"," +
            "       \"name\": \"null\"" +
            "     }" +
            "   ]" +
            "" +
            " }," +
            " \"possibleTreatment\":[" +
            "   {\"@type\":\"Drug\"," +
            "     \"name\":\"null\"," +
            "     \"DoseSchedule\":[" +
            "       {\"doseUnit\":\"null\"}," +
            "       {\"frequency\":\"null\"}" +
            "     ]" +
            "   }," +
            "   {\"@type\":\"Drug\"," +
            "     \"name\":\"null\"," +
            "     \"DoseSchedule\":[" +
            "       {\"doseUnit\":\"null\"}," +
            "       {\"frequency\":\"null\"}" +
            "     ]" +
            "   }" +
            " ]" +
            "}";

    private static final Logger LOGGER = LoggerFactory.getLogger(FillUpData.class);

    public static JSONObject fill(Map<String, String> taggedKeywords) {
        JSONObject obj = null;
        try {
            obj = new JSONObject(JSONTEMPLATE);
        } catch (JSONException e) {
            LOGGER.info("couldn't create JSONLD object...something bad happened.");
            e.printStackTrace();
        }

        for (Map.Entry<String,String> entry : taggedKeywords.entrySet()) {
            if(entry.getValue().compareTo("diseases")==0) {
                try {
                    obj.put("alternateName",entry.getKey());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else if(entry.getValue().compareTo("body-part")==0) {
                try {
                    JSONObject bp = (JSONObject) obj.get("associatedAnatomy");
                    bp.put("name",entry.getKey());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else {
                continue;
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(obj);
        LOGGER.info(jsonOutput);

        LOGGER.info("successfully updated JSON");
        return obj;
    }

    public static JSONObject fillOntology(Map<String,List<Pair>> tags) {
        JSONObject obj = null;
        try {
            obj = new JSONObject(JSONTEMPLATE);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(Map.Entry<String,List<Pair>> entry: tags.entrySet()) {
            if(entry.getKey().compareTo("diseases")==0 && entry.getValue().size()>0) {
                try {
                    obj.put("alternateName",entry.getValue().get(0).getKeyword());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else if(entry.getKey().compareTo("body-part")==0 && entry.getValue().size()>0) {
                try {
                    JSONObject bp = (JSONObject) obj.get("associatedAnatomy");
                    bp.put("name",entry.getValue().get(0).getKeyword());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else if(entry.getKey().compareTo("symptom")==0 && entry.getValue().size()>0) {
                try {
                    JSONObject dd = (JSONObject) obj.get("differentialDiagnosis");
                    JSONArray ds = (JSONArray) dd.get("distinguishingSign");
                    JSONObject jobj = ds.getJSONObject(0);
                    jobj.put("name",entry.getValue().get(0).getKeyword());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else {
                continue;
            }
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(obj);
        LOGGER.info(jsonOutput);

        LOGGER.info("successfully updated JSON");
        return obj;
    }
}
