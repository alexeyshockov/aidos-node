package com.aidos.ari;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import com.aidos.ari.model.Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Snapshot {

    private static final Logger log = LoggerFactory.getLogger(Snapshot.class);

    public static final Map<Hash, Long> initialState = new HashMap<>();

    static {
        String snapshotFileName = "snapshot.csv";
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(snapshotFileName));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains(":")) {
                    break;
                }
                String[] lineSP = line.split(":");
                Hash address = new Hash(lineSP[0]);
                Long value = Long.parseLong(lineSP[1]);
                initialState.put(address, value);
            }
            reader.close();
        } catch (IOException e) {
            log.error(snapshotFileName + " does not exist or not readable");
            System.exit(1);
        }
    }
}
