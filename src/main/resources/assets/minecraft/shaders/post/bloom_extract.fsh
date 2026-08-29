#version 330

uniform sampler2D InSampler;

in vec2 texCoord;

layout(std140) uniform SamplerInfo {
    vec2 OutSize;
    vec2 InSize;
};

layout(std140) uniform BloomConfig {
    float Threshold;
};

out vec4 fragColor;

void main() {
    vec3 color = texture(InSampler, texCoord).rgb;

    float brightness = max(color.r, max(color.g, color.b));

    vec3 bloom = color * smoothstep(
            Threshold,
            Threshold + 0.2,
            brightness
    );

    fragColor = vec4(bloom, 1.0);
}