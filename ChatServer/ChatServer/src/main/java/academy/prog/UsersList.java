package academy.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class UsersList {
    private static final UsersList usr_lst = new UsersList();
    private List<String> list = new ArrayList<>();

    private final Gson gson;

    public static UsersList getInstance() {
        return usr_lst;
    }
    private UsersList() {
        gson = new GsonBuilder().create();
    }

    public synchronized void add(String user) {
        list.add(user);
    }

    public synchronized String toJSON() {
        return gson.toJson(list);
    }

    @Override
    public String toString() {
        return "UsersList{" +
                "list=" + list +
                '}';
    }
}
