package com.tallian.liveinquest.utils;
import android.view.*;
import android.util.*;
import java.net.*;
import java.io.*;
import com.tallian.liveinquest.engine.*;
import android.content.*;
import android.app.*;
import com.tallian.liveinquest.*;

public class FileUtils {
	private static final String LOG_TAG = "file_utils";
	Quest quest;

	public FileUtils(Quest quest){
		this.quest = quest;
	}
	
	public void createFile(String fileName) {
		File file = new File(quest.getApplicationContext().getFilesDir(), fileName);
	}
	
	public void removeFile(String fileName) {
		quest.deleteFile(fileName);
	}
	
	public String readFile(String fileName) {

        Log.d(LOG_TAG, "readFile");

        FileInputStream stream = null;
        StringBuilder sb = new StringBuilder();
        String line;

        try {
            stream = quest.openFileInput(fileName);

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } finally {
                stream.close();
            }

            Log.d(LOG_TAG, "Data from file: " + sb.toString());
			
			return sb.toString();

        } catch (Exception e) {
            Log.d(LOG_TAG, "Файла нет или произошла ошибка при чтении");
			return null;
        }
    }

    public boolean writeToFile(String str, String fileName) {

        Log.d(LOG_TAG, "writeToFile");

        try {
            FileOutputStream outputStream = quest.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(str.getBytes());
            outputStream.close();
			return true;

        } catch (Exception e) {
            Log.d(LOG_TAG, "Произошла ошибка при записи");
			return false;
        }
    }
}
