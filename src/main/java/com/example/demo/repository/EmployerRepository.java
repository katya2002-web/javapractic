package com.example.demo.repository;

import com.example.demo.entity.Employer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.scheduling.annotation.Async;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@org.springframework.stereotype.Repository
public class EmployerRepository implements IEmployerRepository {
    private Gson gson;
    String fileName ="C:\\Users\\rytse\\Desktop\\demo\\src\\main\\resources";

    private Comparator<Employer> idComparator = new Comparator<Employer>() {
        @Override
        public int compare(Employer o1, Employer o2) {
            return o1.getId().compareTo(o2.getId());
        }
    };

    public EmployerRepository(Gson gson) {
        this.gson = gson;
    }
    @Async
    public List<Employer> loadData() {
        var list = new ArrayList<Employer>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            list = gson.fromJson(bufferedReader, new TypeToken<List<Employer>>() {
            }.getType());
            bufferedReader.close();
            System.out.println("Lighting objects have been read from " + fileName + " file.");
            list.sort(idComparator);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Async
    public void writeData(List<Employer> employees) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            gson.toJson(employees, fileWriter);
            fileWriter.close();
            System.out.println("Lighting objects have been saved to " + fileName + " file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Async
    public Employer getByID(Long id) {
        List<Employer> lightings = loadData();
        var buff = lightings.stream().filter(x -> x.getId() == Integer.parseInt(id.toString())).findFirst().get();
        return buff;
    }
    @Async
    public void delete(Long myClassId) {
        List<Employer> myClassList = loadData();
        myClassList.removeIf(x -> myClassId - 1 >= 0 && x.getId() == myClassId);
        writeData(myClassList);
    }
    @Async
    public void save(Employer x) {
        List<Employer> myClassList = loadData();
        if (myClassList.isEmpty()) {
            x.setId(Long.valueOf(1));
        } else {
            x.setId(Long.valueOf(myClassList.get(myClassList.size() - 1).getId() + 1));
        }
        myClassList.add(x);
        writeData(myClassList);
    }
    @Async
    public List<Employer> findAll() {
        List<Employer> myClassList = loadData();
        return myClassList;
    }

    @Override
    public Employer update(Employer lighting) {
        return null;
    }

}
