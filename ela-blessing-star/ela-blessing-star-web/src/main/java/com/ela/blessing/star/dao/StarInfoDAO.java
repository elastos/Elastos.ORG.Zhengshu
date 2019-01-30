package com.ela.blessing.star.dao;


import com.ela.blessing.star.domain.BlessingInfo;
import com.ela.blessing.star.domain.StarInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StarInfoDAO {
    @Select("select id as star_id,name as star_name,blessing_count,utime from star_info order by blessing_count desc")
    List<StarInfo> getStarInfoList();

    @Select("select * from star_info where id = #{id}")
    StarInfo getStarInfo(@Param("id") int id);

    @Update("UPDATE star_info SET blessing_count=#{blessingCount},utime=NOW() WHERE id=#{starId}")
    int updateStarInfo(@Param("starId")int starId,@Param("blessingCount")int blessingCount);
}
