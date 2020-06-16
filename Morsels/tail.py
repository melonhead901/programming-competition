def tail(val, n):
    if n == 0:
        return []
    return list(val[-n:])