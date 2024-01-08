package com.pair.boardspring.board.service;

import com.pair.boardspring.board.dto.BoardDto;
import com.pair.boardspring.board.mapper.BoardMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.pair.boardspring.board.entity.BoardEntity;
import com.pair.boardspring.board.repository.BoardRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Service
@AllArgsConstructor
public class BoardService {
    private final BoardRepository repository;
    private final BoardMapper mapper;

    public BoardEntity save(BoardDto.Post postDto){
        // 예시: 서버 파일 시스템에 저장
        // 이미지 파일을 저장할 경로 설정
//        String filePath = "/path/to/save/images/" + postDto.getImgFile().getOriginalFilename();
//
//        // 파일 저장
//        try {
//            Files.copy(postDto.getImgFile().getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            e.printStackTrace();  // 예외 처리 필요
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        // 데이터베이스에는 이미지 파일의 경로를 저장
//        boardEntity.setImagePath(filePath);

        BoardEntity createBoardEntity = mapper.boardPostDtoToBoardEntity(postDto);
        repository.save(createBoardEntity);
        return createBoardEntity;
    }

    public BoardEntity update(Long id, BoardDto.Patch patch) {
        BoardEntity updateBoardEntity = mapper.boardPatchDtoToBoardEntity(patch, findBoardById(id));
        repository.save(updateBoardEntity);
        return updateBoardEntity;

    }

    public List<BoardEntity> findAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public BoardEntity findBoardById(Long id){
        return repository.findById(id).orElseThrow();
    }
}