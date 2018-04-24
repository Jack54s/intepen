package com.jack.config;

import com.jack.intepen.entity.Threshold;
import org.springframework.context.annotation.Configuration;

import java.util.*;

/**
 * Created by 11407 on 24/024.
 */
@Configuration
public class ThresholdConfiguration implements Observer {

    public Map<String, String> thresholdMap = new HashMap<>();

    @Override
    public void update(Observable o, Object arg) {

        Threshold threshold = (Threshold)o;

        if(thresholdMap.containsKey(threshold.getItem())){
            thresholdMap.replace(threshold.getItem(), threshold.getThreshold());
        }
        else{
            thresholdMap.put(threshold.getItem(), threshold.getThreshold());
        }
    }
}
