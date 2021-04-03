package ua.com.foxminded.classtimetable.domain.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class LessonDto {

    private int id;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private int classroomId;

    private int courseId;

    private int teacherId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonDto lessonDto = (LessonDto) o;
        return id == lessonDto.id && classroomId == lessonDto.classroomId && courseId == lessonDto.courseId &&
                teacherId == lessonDto.teacherId && Objects.equals(date, lessonDto.date) &&
                Objects.equals(startTime, lessonDto.startTime) && Objects.equals(endTime, lessonDto.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, startTime, endTime, classroomId, courseId, teacherId);
    }

    @Override
    public String toString() {
        return "LessonDto{" +
                "id=" + id +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", classroomId=" + classroomId +
                ", courseId=" + courseId +
                ", teacherId=" + teacherId +
                '}';
    }

}
