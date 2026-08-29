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
    //grayscale bias
    float maxChannel = max(color.r, max(color.g, color.b));
    float minChannel = min(color.r, min(color.g, color.b));

    float grayness = 1.0 - (maxChannel - minChannel);
    float luminance = dot(color, vec3(0.299, 0.587, 0.114));
    float brightness = mix(luminance, grayness, 0.35);

    float bloomFactor = smoothstep(
            Threshold,
            Threshold + 0.1,
            brightness
    );

    fragColor = vec4(color * bloomFactor, 1.0);
}