package com.ela.blessing.star.dao;


import com.ela.blessing.star.domain.BlessingInfo;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface BlessingInfoDAO {
    @Select("select * from blessing_info")
    List<BlessingInfo> getBlessingInfo();
}
