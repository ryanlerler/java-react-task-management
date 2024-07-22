import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import { TaskItem } from '../TaskItem';
import { Task } from '../../types/Task';

const task: Task = {
  id: '1',
  name: 'Test Task',
  completed: false,
};

const mockDeleteTask = jest.fn();

describe('TaskItem', () => {
  it('should render task and handle delete', async () => {
    render(<TaskItem task={task} deleteTask={mockDeleteTask} />);

    expect(screen.getByText('Test Task')).toBeInTheDocument();

    fireEvent.click(screen.getByRole('button', { name: /delete/i }));

    await waitFor(() => {
      expect(mockDeleteTask).toHaveBeenCalledWith('1');
    });

    await waitFor(() => {
      expect(mockDeleteTask).toHaveBeenCalledTimes(1);
    });
  });
});
