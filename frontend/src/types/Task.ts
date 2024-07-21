export type Task = {
  id: string;
  details?: string;
  completed: boolean;
  createdDate?: string;
  updatedDate?: string;
} & CreateTask;

export type CreateTask = {
  name: string;
};
