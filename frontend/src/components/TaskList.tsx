import React, { useEffect, useState } from "react";
import { List, message } from "antd";
import { AddTaskForm } from "./AddTaskForm";
import { CreateTask, Task } from "../types/Task";
import {
  createTask,
  fetchAllTasks,
  deleteTask as deleteTaskApi
} from "../api/TaskAPI";
import { TaskItem } from "./TaskItem";

const TaskList: React.FC = () => {
  const [tasks, setTasks] = useState<Task[]>([]);

  const fetchTasks = async () => {
    const tasks = await fetchAllTasks().then((response) => response.content);
    setTasks(tasks);
  };

  useEffect(() => {
    fetchTasks();
  }, []);

  const addTask = (task: CreateTask) => {
    createTask(task).then((response) => {
      message.success("Task added!");
      fetchTasks();
    });
  };

  const deleteTask = (id: string) => {
    deleteTaskApi(id).then((response) => {
      message.success("Task deleted!");
      fetchTasks();
    });
  }


  // TODO: Implement the addSubTask function
  // This function should take a parentId and a name as arguments
  // It should create a new subtask with the given name and parentId
  // After creating the subtask, refetch the tasks
  // Make sure to display a success message after adding the subtask

  // TODO: Implement the toggleTaskComplete function
  // This function should take an taskId as an argument
  // It should toggle the completion status of the task with the given taskId
  // It should also toggle the completion status of all subtasks of the task
  // After toggling the completion status, refetch the tasks

  // TODO: Implement the toggleSubTaskComplete function
  // This function should take a taskId and a subTaskId as arguments
  // It should toggle the completion status of the subtask with the given subTaskId
  // It should also toggle the completion status of the parent task if all subtasks are completed
  // After toggling the completion status, refetch the tasks

  return (
    <div style={{ padding: "20px" }}>
      <AddTaskForm onFormSubmit={addTask} buttonLabel="Add Task" />
      <List
        locale={{ emptyText: "No tasks" }}
        dataSource={tasks}
        renderItem={(task) => (
          <List.Item key={task.id}>
            <TaskItem
              task={task}
              deleteTask={deleteTask}
            />
          </List.Item>
        )}
        style={{ marginTop: "20px" }}
      />
    </div>
  );
};

export default TaskList;
