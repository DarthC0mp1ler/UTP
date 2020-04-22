package tests;

import Implementation.dtos.GroupDTO;
import Implementation.repositories.GroupRepository;
import Implementation.repositories.IGroupRepository;
import org.junit.Assert;

import org.junit.Test;


import java.util.Collections;


public class GroupRepositoryTest extends RepositoryTestBase<GroupDTO, IGroupRepository> {

    @Test
    public void add() {
        GroupDTO groupDTO = new GroupDTO(7, "GroupAdd", "added group");
        _repository.add(groupDTO);

        Assert.assertEquals(_repository.getCount(), 1);
        Assert.assertTrue(_repository.exists(groupDTO));
    }

    @Test
    public void update() {
        GroupDTO groupDTO1 = new GroupDTO(1, "GroupAdd", "added group");
        _repository.add(groupDTO1);
        Assert.assertTrue(_repository.exists(groupDTO1));

        String name = "groupUpdate";
        String description = "Updated group";

        GroupDTO groupDTO2 = new GroupDTO(1, name, description);
        _repository.update(groupDTO2);
        Assert.assertTrue(_repository.exists(groupDTO2));

        Assert.assertEquals(_repository.getCount(), 1);
        Assert.assertEquals(_repository.findById(1).getName(), name);
        Assert.assertEquals(_repository.findById(1).getDescription(), description);
    }

    @Test
    public void addOrUpdate() {
        String name1 = "groupAdd";
        String description1 = "added group";
        GroupDTO groupDTO1 = new GroupDTO(2, name1, description1);
        _repository.addOrUpdate(groupDTO1);

        Assert.assertEquals(_repository.getCount(), 1);
        Assert.assertEquals(_repository.findById(2).getName(), name1);
        Assert.assertEquals(_repository.findById(2).getDescription(), description1);

        String name2 = "groupUpdate";
        String description2 = "Updated group";
        GroupDTO groupDTO2 = new GroupDTO(2, name2, description2);
        _repository.addOrUpdate(groupDTO2);

        Assert.assertEquals(_repository.getCount(), 1);
        Assert.assertEquals(_repository.findById(2).getDescription(), description2);
        Assert.assertEquals(_repository.findById(2).getName(), name2);
    }

    @Test
    public void delete() {
        String name = "groupAdd";
        String desc = "something";
        GroupDTO groupDTO = new GroupDTO(2, name, desc);
        _repository.add(groupDTO);
        Assert.assertTrue(_repository.exists(groupDTO));

        Assert.assertEquals(_repository.findById(2).getName(), name);
        Assert.assertEquals(_repository.getCount(), 1);

        groupDTO = new GroupDTO(2, name, desc);
        _repository.delete(groupDTO);
        Assert.assertEquals(_repository.getCount(), 0);
    }

    @Test
    public void findById() {
        int id = 3;
        GroupDTO groupDTO1 = new GroupDTO(id, "some name", "description");
        _repository.add(groupDTO1);
        Assert.assertTrue(_repository.exists(groupDTO1));

        GroupDTO groupDTO2 = _repository.findById(id);
        Assert.assertTrue(groupDTO1.equals(groupDTO2));
    }

    @Test
    public void findByName() {

        GroupDTO groupDTO = new GroupDTO(0, "GroupUpdate", "Updated group");
        _repository.add(groupDTO);

        Assert.assertEquals(_repository.findByName("GroupUpdate"), Collections.singletonList(groupDTO));
    }

    @Override
    protected IGroupRepository Create() {
        return new GroupRepository();
    }

}