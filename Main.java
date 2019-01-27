package com.bastian.codeNoSQL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {

    public static void JsonReading(String fileName)
    {   String result = "";
        /*JSONParser parser = new JSONParser();
        File jsonFile = new File(fileName);*/
        try {
            final FileWriter writer = new FileWriter("fichierNettoye.json");

            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();


            while (line != null) {
                StringBuilder correctedLine = new StringBuilder();
                for(int i = 0; i<line.length(); i++)
                {
                    if((line.charAt(i) == '$') || (line.charAt(i) == '-'))
                    {

                    }
                    else if(line.charAt(i) == '_')
                    {
                        correctedLine.append("rater");
                    }
                    else
                    {
                        correctedLine.append((line.charAt(i)));
                    }
                }
                line = br.readLine();
                writer.write("\n");
                writer.write(correctedLine.toString());
            }

        } catch(Exception e) {
            e.printStackTrace();
        }


    }

    public static void InsertInto(String fileName)
    {

        try {
            final FileWriter writer = new FileWriter("fichierPeuplement.json");

            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            JSONParser parser = new JSONParser();


            while (line != null) {
                String insert = "INSERT INTO MovieLens (idMovieLens, age, gender, movie, name, occupation) VALUES (";
                StringBuilder correctedLine = new StringBuilder();
                line = br.readLine();

                JSONObject jsonLine = (JSONObject) parser.parse(line);
                String idMovieLens = "\"" + (String)jsonLine.get("oid") + "\"";
                String age = String.valueOf(jsonLine.get("age"));
                String gender = "\"" + (String)jsonLine.get("gender") + "\"";
                String name = "\"" + (String)jsonLine.get("name") +"\"";
                String occupation = "\"" + (String) jsonLine.get("occupation") + "\"";

                String movieId = String.valueOf(jsonLine.get("id"));
                String movieRating = String.valueOf(jsonLine.get("rating"));
                String movieTimestamp = String.valueOf(jsonLine.get("timestamp"));
                String movieTitle = "\"" + (String) jsonLine.get("title") + "\"";

                insert = insert + idMovieLens + "," + age + "," + gender + "," + "{" + movieId + "," + movieRating + "," + movieTimestamp + "," + movieTitle + "}," + name + "," + occupation + ");";

                writer.write("\n");
                writer.write(insert.toString());
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }






    public static void main(String[] args){

        //JsonReading("MovieLens_ratingUsers.json");
        InsertInto("fichierNettoye.json");
    }


}
