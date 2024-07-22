// TODO: Update the TaskItem component that displays a task and its subtasks
// The TaskItem component should take the following props:
// - task: Task - The task object to display
// - addSubTask: (index: string, subTask: string) => void - A function to add a subtask to the task
// - toggleTaskComplete: (index: string) => void - A function to toggle the completion status of the task
// - toggleSubTaskComplete: (taskIndex: string, subTaskIndex: string) => void - A function to toggle the completion status of a subtask
// The TaskItem component should render the following:
// - A Checkbox component to display the task name and toggle the completion status of the task
// - A list of subtasks, each displayed as a Checkbox component
// - An AddTaskForm component to add new subtasks to the task
// - The task name and subtask names should have a line-through style when completed
// - The TaskItem component should be a functional component
// - Make sure to import any necessary dependencies
// - Make sure to export the TaskItem component

// Solution
import React from "react";
import { Button, Checkbox } from "antd";
import { Task } from "../types/Task";

interface TaskItemProps {
  task: Task;
  // TODO: Implement the necessary props to perform the following actions
  // - Add a subtask to the task
  // - Toggle the completion status of the task
  // - Toggle the completion status of a subtask
  deleteTask: (id: string) => Promise<void>;
}

export const TaskItem: React.FC<TaskItemProps> = ({
  task, deleteTask
}) => {
  return (
    <div style={{ width: "100%" }}>
      <div style={{ display: "flex", alignItems: "center" }}>
        <Checkbox
          checked={task.completed}
          onChange={() => { }}
        >
          <span
            style={{ textDecoration: task.completed ? "line-through" : "none" }}
          >
            {task.name}
          </span>
        </Checkbox>
        <Button type="link" danger onClick={() => deleteTask(task.id)}>
          Delete 
        </Button>
      </div>
      {
        //TODO: Implement the Subtasks functions here 
      }
    </div>
  );
};
