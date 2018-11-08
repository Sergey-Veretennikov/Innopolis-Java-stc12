package ru.innopolis.hw20.repository.dao;

import ru.innopolis.hw20.pojo.Group;

interface GroupDao extends AutoCloseable {

    boolean addGroup(Group group);

    Group getGroupById(int id);

    Group getGroupByName(String groupName);

    boolean update(Group group);

    boolean deleteGroupById(int id);

    boolean deleteGroupByName(Group group);
}
