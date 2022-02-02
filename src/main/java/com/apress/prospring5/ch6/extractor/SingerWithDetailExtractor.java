package com.apress.prospring5.ch6.extractor;

import com.apress.prospring5.ch6.entities.Album;
import com.apress.prospring5.ch6.entities.Singer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingerWithDetailExtractor implements ResultSetExtractor<List<Singer>> {

    @Override
    public List<Singer> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, Singer> singerMap = new HashMap<>();
        while (rs.next()) {
            Long id = rs.getLong("id");
            Singer singer = singerMap.get(id);
            if (singer == null) {
                singer = createSinger(id, rs);
                singerMap.put(id, singer);
            }
            Long albumId = rs.getLong("album_id");
            if (albumId > 0) {
                Album album = createAlbum(id, albumId, rs);
                singer.addAlbum(album);
            }
        }

        return new ArrayList<>(singerMap.values());
    }

    private Album createAlbum(Long singerId, Long albumId, ResultSet resultSet) throws SQLException {
        Album album = new Album();
        album.setId(albumId);
        album.setSingerId(singerId);
        album.setTitle(resultSet.getString("title"));
        album.setReleaseDate(resultSet.getDate("release_date"));
        return album;
    }

    private Singer createSinger(Long id, ResultSet resultSet) throws SQLException {
        Singer singer = new Singer();
        singer.setId(id);
        singer.setFirstName(resultSet.getString("first_name"));
        singer.setLastName(resultSet.getString("last_name"));
        singer.setBirthDate(resultSet.getDate("birth_date"));
        singer.setAlbums(new ArrayList<>());
        return singer;
    }
}
