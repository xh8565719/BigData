package com.yjsj.transformer.mr.nu;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;

import com.yjsj.common.GlobalConstants;
import com.yjsj.transformer.model.dim.StatsUserDimension;
import com.yjsj.transformer.model.dim.base.BaseDimension;
import com.yjsj.transformer.model.value.BaseStatsValueWritable;
import com.yjsj.transformer.model.value.reduce.MapWritableValue;
import com.yjsj.transformer.mr.IOutputCollector;
import com.yjsj.transformer.service.IDimensionConverter;

public class StatsUserNewInstallUserCollector implements IOutputCollector {

    @Override
    public void collect(Configuration conf, BaseDimension key, BaseStatsValueWritable value, 
    		PreparedStatement pstmt, IDimensionConverter converter) 
    				throws SQLException, IOException {
        StatsUserDimension statsUserDimension = (StatsUserDimension) key;
        MapWritableValue mapWritableValue = (MapWritableValue) value;
        IntWritable newInstallUsers = (IntWritable) mapWritableValue.getValue().get(new IntWritable(-1));

        int i = 0;
        pstmt.setInt(++i, converter.getDimensionIdByValue(statsUserDimension.getStatsCommon().getPlatform()));
        pstmt.setInt(++i, converter.getDimensionIdByValue(statsUserDimension.getStatsCommon().getDate()));
        pstmt.setInt(++i, newInstallUsers.get());
        pstmt.setString(++i, conf.get(GlobalConstants.RUNNING_DATE_PARAMES));
        pstmt.setInt(++i, newInstallUsers.get());
        pstmt.addBatch();//往批处理放入数据
    }

}
