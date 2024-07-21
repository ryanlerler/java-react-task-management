import { CreateTask } from "../types/Task";
import { Get, Post } from "./API";

const fetchAllTasks = async () => {
  const response = await Get("/tasks/", {});
  console.log(response);
  return response;
};

const createTask = async (task: CreateTask) => {
  const response = await Post("/tasks/task", task);
  return response;
};

export { fetchAllTasks, createTask };

// TODO: Implement the following functions
// - toggleTaskCompletion
// - getTask
// - updateTask
// - deleteTask

