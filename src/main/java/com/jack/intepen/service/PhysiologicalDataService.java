package com.jack.intepen.service;

import com.jack.intepen.dao.PhysiologicalDataDao;
import com.jack.intepen.entity.PhysiologicalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by 11407 on 27/027.
 */
@Service
public class PhysiologicalDataService {

    @Autowired
    private PhysiologicalDataDao physiologicalDataDao;

    public List<PhysiologicalData> getPhysiologicalDataByDeviceId(String deviceId){
        return physiologicalDataDao.queryPhysiologicalDataByDeviceId(deviceId);
    }

    public List<PhysiologicalData> getPhysiologicalDataByDeviceIdAndDatetimeZone(String deviceId, Timestamp datetimeA, Timestamp datetimeB){
        return physiologicalDataDao.queryPhysiologicalDataByDeviceIdAndDatetimeZone(deviceId, datetimeA, datetimeB);
    }

    public List<PhysiologicalData> getPhysiologicalDataByDeviceIdAndDatetime(String deviceId, Timestamp datetime){
        return physiologicalDataDao.queryPhysiologicalDataByDeviceIdAndDatetimeZone(deviceId, datetime, datetime);
    }
}
