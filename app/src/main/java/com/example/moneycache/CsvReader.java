package com.example.moneycache;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.CSVIterator;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;


    /**
     * CsvReader reads a csv file, creates Bank_data objects out of the data, and stores the objects
     * into a List. That List is changed to Json format and written to a txt file.
     * method: readCSVFile(file)
     * method: writeToJson(filename)
     * author: Dixie Cravens
     */

    public class CsvReader {
        //Pattern pattern = Pattern.compile(",");
        //static List<Bank_data> data;
        static List<String> colNames = new ArrayList<>();
        static List<List<String>> records = new ArrayList<>();
        static int date;
        static int description;
        static int amount;
        static int amount2;

        /**
         * Method to create the List of column names from csv file.
         * Used in the getColumnIndex() method.
         * @param file file to be read---will come from readCSVFile()
         */
        public static void setUpCSV(String file) {
            //TODO:this information will need to saved for each user--shared pref? or database?
            try {
                BufferedReader in = new BufferedReader(new FileReader(file));
                String line = in.readLine();
                String[] header = line.split(",");
                colNames.addAll(Arrays.asList(header));
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            } catch (java.io.IOException IOException) {
                IOException.printStackTrace();
            }
        }

        /**
         * Method to print to screen the headers in the User's csv file so the
         * User can match their csv file columns to the Bank_data object data.
         * Set values for date, description, and amount fields to be used as corresponding Indexes
         * when collecting data to build Bank_data object.
         */
        public static void getColumnIndex() {
            int index = 0;
            for (String n : colNames) {
                System.out.println(index++ +": " + n);
            }
            //have user pick number that corresponds to date, then description, then amount--or just click on the line that matches
            //what do I do if there is a column for debits and a column for credits?
            Scanner scanner = new Scanner(System.in);

            System.out.println("Which [number] corresponds with the transaction DATE field?");
            date = scanner.nextInt();
            System.out.println("Which [number] corresponds with the transaction DESCRIPTION field?");
            description = scanner.nextInt();
            System.out.println("Which [number] corresponds with the (first or only) transaction AMOUNT field?");
            amount = scanner.nextInt();
            System.out.println("Which [number] corresponds with the (second) transaction AMOUNT field? Enter '99' if not");
            amount2 = scanner.nextInt();

        }

        /**
         * readCSVFile() reads a csv file into a List named 'data' of type Bank_data, for use by writeToJson().
         * This selects the data, collected by column, by stating the (name of the) index of the data wanted.
         * (My csv file has 7 columns, I only wanted 3.)
         * [Code example from: https://dzone.com/articles/how-to-convert-csv-to-json-in-java
         * --except I used Gson instead of Jackson.]
         * @param file is CSV file to be read
         * author: Dixie Cravens
         */
        //public void readCSVFile(String file) {
        //The following 2 lines (methods) only need to run for the first time a unique csv file is run for the user.
        // Afterwards, the corresponding indexes should be saved for future use.
        // TODO: A new method will have to be written, which substitutes the unique return statement and used for subsequent readings of csv file.

        public static void readCSVFile(String file) {
            setUpCSV(file);
            getColumnIndex();
            CSVIterator iterator = null;
            try {
                iterator = new CSVIterator(new CSVReader(new FileReader(file)));
            } catch (IOException | CsvValidationException e) {
                e.printStackTrace();
            }

            for (CSVIterator it = iterator; Objects.requireNonNull(it).hasNext(); ) {
                String[] values = it.next();
                List<String> importantValues = new ArrayList<>();
                if (amount2 != 99) {
                    importantValues.add(values[date]);
                    importantValues.add(values[description]);
                    importantValues.add(values[amount]);
                    importantValues.add(values[amount2]);
                    records.add(importantValues);
                } else {
                    importantValues.add(values[date]);
                    importantValues.add(values[description]);
                    importantValues.add(values[amount]);
                    records.add(importantValues);

                }
            }
        }

        /**
         * Writes a List of Bank_data objects (stored in 'data') to Json format
         * using Gson.
         * [Code examples from week 03 team assignment were helpful (Team-01-03 in IntelliJ).]
         * @param filename is the name of the .txt file to write the Json to.
         * TODO: append new data to end of file instead of overwriting. Needed?
         */
        public void writeToJson(String filename) {
            //for compact printing of JSON string--all on one line(may be preferable for shared preferences storage):
            //Gson gson = new Gson();
            //for pretty printing (formatted):
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            String newData = gson.toJson(records);
            //Save the string to a file.
            try (Writer w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8))) {
                w.write(newData);
            } catch (FileNotFoundException e) {
                System.out.println("File not found " + e);
            } catch (IOException ee) {
                System.out.println("IO exception: " + ee);
            }

        }
    }


