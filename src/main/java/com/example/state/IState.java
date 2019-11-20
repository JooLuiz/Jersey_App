package com.example.state;

public interface IState<T> {
	public T nextState(T item);
}
