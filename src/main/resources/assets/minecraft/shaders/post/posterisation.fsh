#version 330

uniform sampler2D InSampler;

in vec2 texCoord;

out vec4 fragColor;

layout(std140) uniform PosterisationConfig {
    vec3 colorSteps;
};

void main() {
    vec3 color = texture(InSampler, texCoord).rgb;
    color = round(color * colorSteps) / colorSteps;
    fragColor = vec4(color, 1.0);
}