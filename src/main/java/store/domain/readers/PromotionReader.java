package store.domain.readers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import store.domain.store.item.Promotion;

public class PromotionReader {

    public List<Promotion> read(String path) {
        List<Promotion> result = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split(",");

                String name = splitLine[0];
                int buy = Integer.parseInt(splitLine[1]);
                int get = Integer.parseInt(splitLine[2]);

                LocalDate startLocalData = LocalDate.parse(splitLine[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDateTime startDate = startLocalData.atStartOfDay();

                LocalDate endLocalData = LocalDate.parse(splitLine[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDateTime endDate = endLocalData.atStartOfDay();

                Promotion promotion = new Promotion(name, buy, get, startDate, endDate);
                result.add(promotion);

            }
        } catch (IOException e) {
            throw new IllegalStateException("재품 등록 오류");
        }

        return result;
    }

}
