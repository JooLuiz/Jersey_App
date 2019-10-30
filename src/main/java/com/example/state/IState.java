package com.example.state;

public interface IState<T> {
	public T setState(T item);
	public T getState();
}
