package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {

    @DataProvider
    public Iterator<Object[]> loginData () {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{User.builder().
                email("juliyabee@gmail.com").
                password("Pass@word1").build()});
        list.add(new Object[]{User.builder().
                email("hatum.testing@gmail.com").
                password("Hatum21$").build()});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> wrongEmailData() throws IOException {
        List<Object[]> list=new ArrayList<>();
        BufferedReader reader = new BufferedReader
                (new FileReader(new File("src/test/resources/data1.csv")));

        String line = reader.readLine();

        while (line!=null){
            String[] split = line.split(",");
            list.add(new Object[]{User.builder().email(split[0]).
                    password(split[1]).build()});
            line = reader.readLine();

        }

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> wrongPasswordData() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader
                (new FileReader(new File("src/test/resources/data2.csv")));

        String line = reader.readLine();

        while (line!=null){
            String[] split = line.split(",");
            list.add(new Object[]{User.builder().email(split[0]).
                    password(split[1]).build()});
            line = reader.readLine();

        }

        return list.iterator();
    }

}

