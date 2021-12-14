package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{title}, #{description}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addNote(Note note);

    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    @Results({
            @Result(id = true, property = "id", column = "noteid"),
            @Result(property = "title", column = "notetitle"),
            @Result(property = "description", column = "notedescription"),
            @Result(property = "userid", column = "userid")
    })
    List<Note> getAllNotes(Integer userid);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteid}")
    int deleteNote(int noteid);

    @Update("UPDATE NOTES " +
            "SET notetitle = #{title}, notedescription = #{desc} " +
            "WHERE noteid = #{id}")
    int updateNote(Integer id, String title, String desc);
}
