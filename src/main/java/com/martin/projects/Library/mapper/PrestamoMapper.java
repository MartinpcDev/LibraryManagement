package com.martin.projects.Library.mapper;

import com.martin.projects.Library.dto.request.SavePrestamo;
import com.martin.projects.Library.dto.request.UpdatePrestamo;
import com.martin.projects.Library.dto.response.PrestamoDto;
import com.martin.projects.Library.persistence.entity.Book;
import com.martin.projects.Library.persistence.entity.Prestamo;
import com.martin.projects.Library.persistence.entity.User;
import com.martin.projects.Library.util.PrestamoStatus;
import java.util.List;

public class PrestamoMapper {

  public static PrestamoDto toPrestamoDto(Prestamo prestamo) {
    if (prestamo == null) {
      return null;
    }

    return new PrestamoDto(
        prestamo.getId(),
        prestamo.getStatus(),
        prestamo.getStartDate(),
        prestamo.getEndDate(),
        BookMapper.toBookDto(prestamo.getBook()),
        UserMapper.toUserDto(prestamo.getUser())
    );
  }

  public static List<PrestamoDto> toPrestamoDtoList(List<Prestamo> prestamos) {
    if (prestamos == null) {
      return null;
    }

    return prestamos.stream()
        .map(PrestamoMapper::toPrestamoDto)
        .toList();
  }

  public static Prestamo toSavePrestamoEntity(SavePrestamo prestamoDto, Book book, User user) {
    if (prestamoDto == null) {
      return null;
    }

    Prestamo prestamo = new Prestamo();
    prestamo.setStartDate(prestamoDto.getStartDate());
    prestamo.setEndDate(prestamoDto.getEndDate());
    prestamo.setBook(book);
    prestamo.setUser(user);

    return prestamo;
  }

  public static Prestamo toUpdatePrestamoEntity(UpdatePrestamo prestamoDto, Book book, User user) {
    if (prestamoDto == null) {
      return null;
    }

    Prestamo prestamo = new Prestamo();
    prestamo.setStartDate(prestamoDto.getStartDate());
    prestamo.setEndDate(prestamoDto.getEndDate());
    prestamo.setStatus(PrestamoStatus.valueOf(prestamoDto.getStatus()));
    prestamo.setBook(book);
    prestamo.setUser(user);

    return prestamo;
  }

  public static void updatePrestamoEntity(Prestamo oldPrestamo, UpdatePrestamo prestamoDto,
      Book book, User user) {
    if (oldPrestamo == null || prestamoDto == null || book == null || user == null) {
      return;
    }

    oldPrestamo.setStartDate(prestamoDto.getStartDate());
    oldPrestamo.setEndDate(prestamoDto.getEndDate());
    oldPrestamo.setStatus(PrestamoStatus.valueOf(prestamoDto.getStatus().toUpperCase()));
    oldPrestamo.setBook(book);
    oldPrestamo.setUser(user);
  }
}