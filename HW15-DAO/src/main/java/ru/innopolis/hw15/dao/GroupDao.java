package ru.innopolis.hw15.dao;

import ru.innopolis.hw15.pojo.Group;

public interface GroupDao extends AutoCloseable {

    boolean addGroup(Group group);

    Group getGroupById(int id);

    Group getGroupByName(String groupName);

    boolean update(Group group);

    boolean deleteGroupById(int id);

    boolean deleteGroupByName(Group group);
}
