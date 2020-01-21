package com.nwchat.repository;

import com.nwchat.entity.OrderEntity;
import com.nwchat.entity.ReportEntity;
import com.nwchat.entity.UserEntity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TestReportRepo extends AbstTestRepo<ReportEntity> implements ReportRepository  {
    List<ReportEntity> reportEntityList = new ArrayList<>();
    public TestReportRepo() {
        TestOrderRepo testOrderRepo = new TestOrderRepo();
        OrderEntity orderEntity = testOrderRepo.findById(0).get();

        ReportEntity reportEntity = new ReportEntity();
        reportEntity.setId(2);
        reportEntity.setState(0);
        reportEntity.setNum("123");
        reportEntity.setText("123");
        reportEntity.setCreatorId(2);
        reportEntity.setOrderId(0);
        reportEntity.setAt(new Date(0));
        reportEntity.setOrdersByOrderId(orderEntity);

        ReportEntity reportEntity1 = new ReportEntity();
        reportEntity1.setId(1);
        reportEntity1.setState(1);
        reportEntity1.setNum("asd");
        reportEntity1.setText("sadad");
        reportEntity1.setCreatorId(2);
        reportEntity1.setAt(new Date(0));
        reportEntity1.setOrdersByOrderId(orderEntity);



        reportEntityList.add(reportEntity);
        reportEntityList.add(reportEntity1);
        saveAll(reportEntityList);
    }

    @Override
    public List<ReportEntity> findAllByCreatorIdEquals(Integer id) {

        ReportEntity reportEntity = new ReportEntity();
        reportEntity.setId(2);
        reportEntity.setState(0);
        reportEntity.setNum("123");
        reportEntity.setText("123");
        reportEntity.setCreatorId(2);
        reportEntity.setAt(new Date(0));
        List<ReportEntity> reportList = new ArrayList<>();
        reportList.add(reportEntity);
        return reportList;
    }

    @Override
    public List<ReportEntity> findAllByStateEquals(Integer state) {

        TestOrderRepo testOrderRepo = new TestOrderRepo();
        OrderEntity orderEntity = testOrderRepo.findById(0).get();
        ReportEntity reportEntity1 = new ReportEntity();
        reportEntity1.setId(1);
        reportEntity1.setState(1);
        reportEntity1.setNum("asd");
        reportEntity1.setText("sadad");
        reportEntity1.setCreatorId(2);
        reportEntity1.setAt(new Date(0));
        reportEntity1.setOrdersByOrderId(orderEntity);
        List<ReportEntity> reportList = new ArrayList<>();
        reportList.add(reportEntity1);
        return reportList;
    }
}
