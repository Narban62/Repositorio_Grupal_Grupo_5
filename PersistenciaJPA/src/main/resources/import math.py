import math

# Datos
n = 13000000
k = 1500
n_k = n - k

# Cálculo de la raíz cuadrada
sqrt_term = math.sqrt(n / (2 * math.pi * k * n_k))

# Cálculo del cociente de potencias
power_term = (n**n) / ((k**k) * (n_k**n_k))

# Combinatoria usando la aproximación de Stirling
combinatoria_stirling = sqrt_term * power_term
combinatoria_stirling
