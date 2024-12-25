#version 330 core

out vec4 FragColor;

in vec2 TexCoord;

uniform vec3 bladeColor;       // Color of the blade
uniform float glowIntensity;   // Intensity of the glow
uniform float time;            // Time variable for animation

void main() {
    // Centered radial gradient for blade edges
    float dist = length(TexCoord - vec2(0.5, 0.5));
    float glow = exp(-pow(dist * 10.0, 2.0)) * glowIntensity;

    // Pulsating effect
    glow *= 0.5 + 0.5 * sin(time * 5.0);

    // Base blade color
    vec3 color = bladeColor * glow;

    // Fade out edges
    float alpha = 1.0 - smoothstep(0.4, 0.5, dist);

    FragColor = vec4(color, alpha);
}