package com.jack.intepen.dao;

import com.jack.intepen.entity.PhysiologicalData;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by 11407 on 27/027.
 */
public interface PhysiologicalDataDao {

    int insertPhysiologicalData(PhysiologicalData physiologicalData);
    //int deletePhysiologicalData(PhysiologicalData physiologicalData);
    //int updatePhysiologicalData(PhysiologicalData physiologicalData);
    List<PhysiologicalData> queryPhysiologicalDataByDeviceId(String deviceId);
    List<PhysiologicalData> queryPhysiologicalDataByDeviceIdAndDatetimeZone(@Param("deviceId") String deviceId,
                                                                            @Param("datetimeA") Timestamp datetimeA,
                                                                            @Param("datetimeB") Timestamp datetimeB);
}
