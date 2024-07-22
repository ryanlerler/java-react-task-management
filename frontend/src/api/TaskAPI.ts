import { CreateTask } from "../types/Task";
import { Get, Post, Delete } from "./API";

const fetchAllTasks = async () => {
  const response = await Get("/tasks/", {});
  console.log(response);
  return response;
};

const createTask = async (task: CreateTask) => {
  const response = await Post("/tasks/task", task);
  return response;
};

const deleteTask = async (id: string) => {
  const response = await Delete(`/tasks/task/${id}`);
  return response;
};


export { fetchAllTasks, createTask, deleteTask };

// TODO: Implement the following functions
// - toggleTaskCompletion
// - getTask
// - updateTask
// - deleteTask

